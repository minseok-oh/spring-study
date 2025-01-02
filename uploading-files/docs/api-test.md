# @WebMvcTest

Spring Boot Test를 하기엔 너무 무겁고, `@Controller`와 같은 것만 Context로 할 수 없을까? <br/>
그럴땐 @WebMvcTest를 사용하면 된다! 또한 MockMvc나 DB 설정이 자동으로 된다!

```java
@WebMvcTest(controllers = {
        ExceptionController.class,
        FileUploadController.class
})
public class ApiTest {

    @MockitoBean
    public StorageService storageService;

    @Autowired
    public MockMvc mockMvc;

}
```

위처럼 필요한 Bean으로 등록할 Controller를 쓰고 필요한 Mock을 쓴다. <br/>
내부적으로 Mock에서 필요한 부분을 등록하고 MockMvc로 요청을 하면 테스트 완료!

# MockMvc 활용하기

```java
@Nested
class 기본_페이지 {

    @Test
    void uploadForm_뷰가_반환된다() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("uploadForm"))
                .andExpect(model().attributeExists("files"));
    }
}
```

- 이런식으로 응답값의 header, body, status, view, model 등을 확인할 수 있다.
- 혹시나 내부 의존값에 대한 것도 Mock으로 조정이 가능하다!