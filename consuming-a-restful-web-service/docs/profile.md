# Profile?

Spring 실행 시에 현재 Context가 무엇인 지 profile을 통해 입력할 수 있다 <br>
이를 통해 `dev`, `test`, `prod` 환경에 대한 설정을 따로 해놓을 수 있다 <br>
하지만 이를 사용하기 위해선 하드 코딩된 값을 넣어야 한다는 단점이 존재한다

```java
@Conditional({ProfileCondition.class})
public @interface Profile {
    String[] value();
}
```

- `@Conditional` 어노테이션을 통해 ProfileCondition 클래스를 사용한다
- 내부에서 Condition을 상속해 `matches` 메서드를 구현한 클래스임을 확인할 수 있다
- 그렇다면 우리도 하드 코딩이 아닌 값을 사용하도록 커스텀할 수 있다

# Conditional로 대체하기

- Profile의 값을 구분하기 위한 Enum을 구현한다
- 추상 클래스 BaseProfileCondition에서 matches를 구현한다
  - 이 때, getTargetProfile이라는 추상 메서드를 만들어 둔다
- 이후 이를 상속받아 구현하면 Custom Conditional을 만들 수 있다