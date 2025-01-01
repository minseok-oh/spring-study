# Test 환경에서만 안되는 Reflection

- 배경: `@Table`을 인식하고 Reflection으로 테이블을 생성하는 로직을 구현
- 상황: Test 환경에서만 이를 인식하지 못함!

```text
com.java.study.infra.PureJdbcCustomerRepositoryTest
com.java.study.infra.DataJdbcCustomerRepositoryTest$selectByFirstName_메소드는
com.java.study.infra.JdbcTemplateCustomerRepositoryTest$selectByFirstName_메소드는
com.java.study.infra.PureJdbcCustomerRepositoryTest$selectByFirstName_메소드는
com.java.study.infra.DataJdbcCustomerRepositoryTest
com.java.study.infra.JdbcTemplateCustomerRepositoryTest
com.java.study.context.DatabaseCleaner
com.java.study.context.RepositoryTest
```

- 몇가지 시도를 하다 src/main의 클래스를 못불러오는 것을 확인!
- Test 프로젝트에도 domain들을 넣는 것으로 구현 (JDBC는 Auto Create가 없다고 합니다)
