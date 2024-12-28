# @SpringBootApplication

```java
@SpringBootConfiguration
@EnableAutoConfiguration
@ComponentScan(
    excludeFilters = {@Filter(
    type = FilterType.CUSTOM,
    classes = {TypeExcludeFilter.class}
), @Filter(
    type = FilterType.CUSTOM,
    classes = {AutoConfigurationExcludeFilter.class}
)}
)
@interface SpringBootApplication {
    
}
```

- `@SpringBootConfiguration`을 통해 Bean을 생성하고 Spring Container에 등록한다
- `@EnableAutoConfiguration`을 통해 클래스 경로 기반으로 Bean을 자동 구성하고 조건부나 기본 설정을 한다
- `@ComponentScan`을 통해서 해당 클래스의 패키지와 하위 패키지의 `@Component`를 인식하고 Bean으로 생성한다

# @RestController
```java
@Controller
@ResponseBody
@interface RestController {
    
}
```

- `@Controller`를 통해 DispatcherServlet이 HandlerAdapter를 통해 요청을 위임한디
- 이의 결과를 토대로 ViewResolver를 통해 View를 찾아 반환한다


- View가 아닌 Data(Json)를 반환하기 위해서 `@ResponseBody`를 사용한다
- 즉, 이 2개를 합친 것이 `@RestController`이다

# @RequestParam

- 요청이 들어오면 HandlerMethodArgumentResolver들의 `supportsParameter`가 실행된다
- 해당하는 어노테이션이 나오면 `resolveArgument`를 통해 값이 들어가게 된다


- name(value): 파라미터의 이름이며 변수 이름과 같은 경우 생략이 가능하다
- required: 필수 속성인지에 대한 값으로 기본 값은 true이다
- defaultValue: 파라미터에 값이 없는 경우 기본값을 설정할 수 있다