# @SpringBootTest

```java
@BootstrapWith(SpringBootTestContextBootstrapper.class)
@ExtendWith({SpringExtension.class})
public @interface SpringBootTest {
}
```

- 설정을 통해 `@BootstrapWith(SpringBootTestContextBootstrapper.class)`로 스프링 컨테이너가 만들어진다
- `@ExtendWith({SpringExtension.class})`로 Spring, JUnit의 테스트 기능을 사용할 수 있도록 확장한다
- 이를 통해 테스트를 위한 Spring Context가 구성되고 필요한 Bean들을 구성해줘야 한다
- `@SpringBootTest` 사용 시 webEnvironment으로 port를 랜덤 포트 등으로 변경할 수 있다

# @MockitoBean vs @MockitoSpyBean

- `@MockitoBean`은 해당 클래스의 메서드 내부를 모두 없애고 given을 통해 사용자가 만들도록 한다
- `@MockitoSpyBean`은 사용자 구현이 아닌 부분은 기존의 객체에서 사용하도록 한다
- 결론적으로 구현하지 않은 부분은 기존 객체와 동일하게 하고싶다면 Spy를 아니라면 Mock을 사용하는 것이다 
- Mock은 좀더 자유로운 확장(직렬화 등)이 가능하고 Spy는 기존 객체의 동작을 감시하는 것을 목표로 한다

```java
@BeanOverride(MockitoBeanOverrideProcessor.class)
public @interface MockitoBean {
}
```

- MockitoBeanOverrideProcessor가 내부적으로 Spring Context에 모킹된 빈을 주입한다
- 내부적으로 `@MockitoBean`와 `@MockitoSpyBean`를 구분하여 다른 클래스로 넘긴다
- 해당 어노테이션을 사용하는 것을 지양하고 많은 Mocking이 필요 시 컴포넌트 분리를 고민해본다

# awaitility

```java
@Test
public void reportCurrentTime() {
    await().atMost(Durations.TEN_SECONDS).untilAsserted(() -> {
        verify(tasks, atLeast(2)).reportCurrentTime();
    });
}
```

- 스레드, 시간 제한, 동시성 등을 해결하며 비동기 시스템을 테스트하기는 힘들어 이를 도와주는 라이브러리이다
- 위의 코드를 해석하면 10초동안 tasks의 `reportCurrentTime()`이 2번 이상 실행되는 지 검증한다는 의미이다
- 즉, 5000ms로 설정해두었기 떄문에 비동기 작업이 제대로 수행되면 해당 테스트가 통과된다