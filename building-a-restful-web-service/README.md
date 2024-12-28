### 요구사항

- HTTP GET 요청을 받는 서버입니다
- 다음의 API를 구현합니다

|URI| Response                                  |
|---|-------------------------------------------|
|http://localhost:8080/greeting| ```{"id":1,"content":"Hello, World!"}```  |
|http://localhost:8080/greeting?name={name}| ```{"id":1,"content":"Hello, {name}!"}``` |

