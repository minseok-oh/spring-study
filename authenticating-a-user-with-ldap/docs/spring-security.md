# SecurityFilterChain이란?

- Spring Security에서 Filter를 활용해 보안 관련 작업을 진행하는 것
- doFilter 메소드를 활용해 FilterChain 내의 모든 Filter를 연쇄적으로 확인
- 따라서 각 Filter는 doFilter를 구현해야 하고 SecurityFilterChain은 matches를 구현해야 한다

# Filter vs Interceptor vs AOP

- 공통 로직을 위한 방식이 총 3가지 Filter, Interceptor, AOP가 있다
- 각각의 특징을 살펴보면 다음과 같다

|        | Filter  | Interceptor |AOP|
|--------|---------|-------------|----|
| 단위     | Servlet | Servlet     |Method Proxy|
| 순서     | 1, 5    | 2, 4        |3|
| 실행 메서드 |- init() <br/> - doFilter() <br/> - destory()|- preHandle() <br/> - postHandle() <br/> - afterCompletion()|@Before, @After, @Around...|