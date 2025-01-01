# Pure JDBC

- DriverManager 혹은 DataSource를 통해 Connection을 생성한다
- Connection에서 Statement 혹은 PreparedStatement를 생성해 SQL을 담는다
- Statement를 실행해 ResultSet를 조작하여 결과를 가져온다

```java
@Override
public Customer insert(Customer customer) {
    String insertQuery = "INSERT INTO Customer(firstname, lastname) VALUES (?,?)";

    try (
            Connection connection = dataSource.getConnection();
            PreparedStatement pstmt = connection.prepareStatement(insertQuery, new String[]{"id"})
    ) {

        pstmt.setString(1, customer.getFirstName());
        pstmt.setString(2, customer.getLastName());
        pstmt.executeUpdate();

        ResultSet generatedKeys = pstmt.getGeneratedKeys();
        if (generatedKeys.next()) {
            return new Customer(generatedKeys.getLong(1), customer.getFirstName(), customer.getLastName());
        } else {
            throw new SQLException("생성된 Id가 존재하지 않습니다.");
        }
    } catch (SQLException e) {
        throw new RuntimeException(e);
    }
}
```

- Connection과 PreparedStatement를 사용 후 닫아줘야 한다
- Connection Pool에 대한 생각을 하고 구현해야 한다

# JDBC Template

- JdbcTemplate을 가져와 내부에 구현된 메서드를 사용한다
- execute, excuteUpdate, executeQuery, query 등의 여러 메서드를 제공한다

```java
@Override
public List<Customer> selectByFirstName(String firstName) {
    return jdbcTemplate.query("SELECT id, firstname, lastname FROM Customer WHERE firstname = ?",
            (rs, rowNum) -> new Customer(rs.getLong("id"), rs.getString("firstname"), rs.getString("lastname")),
            firstName);
}
```

# Spring Data JDBC

- 더 쉽게 JPA 처럼 기본적인 SQL에 대한 메서드를 제공한다
- 하지만 @Id, @Table, @Column 등을 제공하지만 자동 테이블 생성은 지원하지 않는다

```java
public interface CrudCustomerRepository extends CrudRepository<Customer, Long> {
    List<Customer> findCustomersByFirstName(String firstName);
}

@Override
public Customer insert(Customer customer) {
    return repository.save(customer);
}
```