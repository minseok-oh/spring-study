# @EnableScheduling

```java
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Import({SchedulingConfiguration.class})
@Documented
public @interface EnableScheduling {
}
```

내부를 살펴보면 `@Import({SchedulingConfiguration.class})`이 있다 <br/>
`SchedulingConfiguration.java`를 보면 ScheduledAnnotationBeanPostProcessor을 빈으로 등록한다 <br/>
내부에 `AnnotationUtils.isCandidateClass(targetClass, List.of(Scheduled.class, Schedules.class))`이 있음을 확인할 수 있다 <br/>
즉, 정리하면 `@EnableScheduling`을 통해 `@Scheduled` 애노테이션이 붙은 것을 모두 인식하게 된다


# @Scheduled

- cron: Cron 표현식을 사용하여 작업 실행 주기를 설정
- zone: 시간대를 설정하며, cron과 함께 사용
- fixedRate(String): 작업 시작부터 다음 작업 시작까지의 고정된 시간 간격(밀리초)을 설정
- fixedDelay(String): 작업 종료 후부터 다음 작업 시작까지의 고정된 지연 시간(밀리초)을 설정
- initialDelay(String): 작업 시작 전 대기 시간(밀리초)을 설정
- timeUnit: 시간 단위를 설정하며 기본값은 MILLISECONDS
- scheduler: 사용자 정의 스케줄러의 이름을 지정