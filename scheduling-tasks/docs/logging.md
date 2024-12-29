# Slf4j는 무엇인가?

- Slf4j는 Facade 즉, 로그 인터페이스라고 생각하면 쉽다
- Log4J나 Logback 등과 같은 실제 로깅 프레임워크에 대한 인터페이스다
- 따라서 Slf4j를 사용 시에는 꼭 구현체도 의존성을 주입해 바인딩을 해주어야 한다
  - BRIDGE : 다른 로깅 API 호출 시 Slf4j 인터페이스로 연결해 대신 처리 → 레거시 코드 처리
  - SLF4J API : 로깅을 위한 추상 메서드(인터페이스)를 제공, 반드시 API, BINDING 1:1 매핑
  - BINDING : 구현체랑 API 연결해줌 → Logger의 API 호출

# @Slf4j

```java
@Provides
public static class HandleSlf4jLog extends JavacAnnotationHandler<lombok.extern.slf4j.Slf4j> {
    @Override public void handle(AnnotationValues<lombok.extern.slf4j.Slf4j> annotation, JCAnnotation ast, JavacNode annotationNode) {
        handleFlagUsage(annotationNode, ConfigurationKeys.LOG_SLF4J_FLAG_USAGE, "@Slf4j", ConfigurationKeys.LOG_ANY_FLAG_USAGE, "any @Log");
        processAnnotation(LoggingFramework.SLF4J, annotation, annotationNode);
    }
}
```

- 위의 Lombok의 `HandleLog.java` 코드를 통해 `@Slf4j`가 붙으면 Logger가 생성된다
- 따라서 여러 곳에서 로그를 남긴다고 한다면 Lombok의 `@Slf4j`를 사용하자

# 언제 그러면 로그를 남겨야 할까?

- 로그를 모든 요청과 응답에 남기기 위해서는 어떻게 해야할까?
- Interceptor와 ExceptionHandler를 만들어 내부에서 로그를 남기는 것이 좋을 것 같다
- 그 이외에는 서비스 요구사항에 맞게 로그도 구현해보자