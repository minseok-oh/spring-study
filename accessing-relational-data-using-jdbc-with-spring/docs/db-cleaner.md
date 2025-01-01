# Database Cleaner?

Test 환경에서 데이터베이스에 대해 동시 작업들이 일어난다 <br/>
그러면 이전에 넣어둔 데이터가 다음의 테스트에 영향을 주지 않을까? <br/>
그래서 테스트가 끝날때마다 해당 테이블을 비워주는 작업을 해야한다!

```java
public void clear() {
    try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASSWORD)) {
        String truncateQuery = "TRUNCATE TABLE ";
        Statement statement = connection.createStatement();

        for (String table: tableNames) {
            statement.execute(truncateQuery + table);
        }
    } catch (Exception e) {
        throw new RuntimeException(e);
    }
}
```

- `TRUNCATE TABLE` 쿼리를 사용해서 해당 테이블을 모두 비워준다
- 각 테스트가 끝날때마다 실행되도록 `@AfterEach`를 사용한다
- 쉬운 삭제를 위해 종속을 설정하기도 한다
- Test Container를 사용하면 테스트마다 새로운 환경이 만들어지나 속도가 느리다