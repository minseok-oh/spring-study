# Spring에서 API를 요청하는 방법

- `HttpURLConnection` / `URLConnection`
- `HttpClient`
- `RestTemplate`
- `WebClient`
- `OpenFeign`

<br>

| 방법                     | 장점                                                                 | 단점                                                                                                  |
|--------------------------|----------------------------------------------------------------------|-------------------------------------------------------------------------------------------------------|
| **HttpURLConnection**   | - Java 기본 제공 클래스, 추가 의존성 필요 없음<br>- 간단한 HTTP 요청에 적합 | - 코드가 복잡하고 가독성이 낮음<br>- 유지보수 어려움<br>- 비동기 처리 불가능                          |
| **HttpClient**           | - 고급 HTTP 요청 설정 가능<br>- 커넥션 풀 및 타임아웃 등 커스터마이징 가능<br>- Apache에서 제공 | - Spring과의 통합이 복잡<br>- 추가 의존성 필요<br>- 코드 작성이 상대적으로 길어짐                    |
| **RestTemplate**         | - Spring에서 제공<br>- 간단한 동기식 요청에 적합<br>- 익숙한 사용법 | - 비동기 처리 불가<br>- Spring 5 이후로 개선 중단                                    |
| **WebClient**            | - 비동기/동기 처리 모두 지원<br>- Reactive Streams 지원<br>- 요청/응답 체인 방식으로 유연함<br>- 간결한 에러 처리 가능 | - 사용법이 비교적 복잡<br>- 학습 곡선이 있음                                                         |
| **OpenFeign**            | - 선언형 인터페이스로 간결한 코드<br>- Spring Cloud와 완벽한 통합<br>- 로드 밸런싱 및 장애 처리 지원 | - 동기식 요청만 기본 지원<br>- Spring Cloud 의존성 필요<br>- 고급 HTTP 설정이 제한적                  |

# Connection Pool에 대하여

### Tomcat의 Connection 관리법

- 먼저, `Tomcat Connection Pool`이라는 용어는 데이터베이스와의 연결에 대해서 말한다
- 사용자가 접근하는 Connection은 `server.xml`에 Connector에서 관리할 수 있다

```xml
<Connector port="8080" protocol="HTTP/1.1"
           maxKeepAliveRequests="100"
           keepAliveTimeout="5000"
           connectionTimeout="20000" />
```

- 위와 같이 Connection에 대한 Pool을 만들어 두는 것을 알 수 있다

### HttpClient vs OkHttp vs NettyClient

1. **Apache HttpClient**:
    - 세부적인 커넥션 풀 설정 가능
    - 엔터프라이즈 환경에서 신뢰할 수 있는 옵션

2. **OkHttp**:
    - 간단한 설정과 사용법으로 소규모 프로젝트 및 클라이언트 중심 애플리케이션에 적합

3. **NettyClient**:
    - 비동기 및 리액티브 애플리케이션에 최적
    - 초기 설정이 복잡하지만 대규모 트래픽 처리에 유리