# 🍃 스프링 공부하기

아래의 스프링 공식 가이드와 인강을 듣고 직접 구현해보며, 개선하며 학습합니다.<br/>
[spring 공식 가이드](https://spring.io/guides)

<br/>

# 🍃 내용 정리

|프로젝트|설명|링크|
|-------|---|-----|
|Building a RESTful Web Service|@RequestParam을 통해 GET 요청 받아 반환하기|[링크](https://spring.io/guides/gs/rest-service)|
|Scheduling Tasks|작업을 스케쥴링하여 5초마다 실행하기|[링크](https://spring.io/guides/gs/scheduling-tasks)|

# 🍃 학습 내용 정리
|설명|위치|
|---|-----|
|Spring Boot의 핵심 애노테이션과 @RequestParam 동작 원리 정리|`building-a-restful-web-service/docs/annotation.md`|
|AtomicLong의 원자성 보장과 Unsafe를 활용한 내부 구현 이해|`building-a-restful-web-service/docs/atomic-long.md`|
|Spring에서 Jackson ObjectMapper 활용과 직렬화/역직렬화 사용법|`building-a-restful-web-service/docs/object-mapper.md`|
|Java Record: 불변 데이터 클래스의 특징과 사용 목적|`building-a-restful-web-service/docs/record.md`|
|Java의 String vs StringBuffer vs StringBuilder 성능 비교와 static 키워드 활용|`building-a-restful-web-service/docs/string.md`|
|Slf4j와 Lombok의 활용 및 로깅 학습|`scheduling-tasks/docs/logging.md`|
|Spring Scheduling의 @EnableScheduling과 @Scheduled 활용 정리|`scheduling-tasks/docs/schedule.md`|
|Spring Boot Test와 Mockito 활용, Awaitility로 비동기 테스트 구현|`scheduling-tasks/docs/test.md`|
