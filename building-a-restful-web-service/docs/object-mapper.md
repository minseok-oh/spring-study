# Object Mapper

### 개념 정리

Spring의 `@ResponseBody`나 `@RestController`를 쓰면 Jackson ObjectMapper로 직렬화함 <br/>
내부적으로 Parser가 분리하고 JsonToken으로 나뉘어 값을 가져올 수 있게 함 <br/>

### 사용 방법

```java
public class ObjectMapperTest {
    ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void writeFileOfGreeting() {
        try {
            Greeting greeting = new Greeting(1L, "Greeting");
            File file = new File("src/greeting.json");

            objectMapper.writeValue(file, greeting);

            var reader = new BufferedReader(new FileReader(file));
            assertThat(reader.readLine()).isEqualTo("{\"id\":1,\"content\":\"Greeting\"}");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void readFileOfGreeting() {
        try {
            Greeting readedGreeting = objectMapper.readValue(new File("src/greeting.json"), Greeting.class);

            assertThat(readedGreeting.id()).isEqualTo(1L);
            assertThat(readedGreeting.content()).isEqualTo("Greeting");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void readStringOfGreeting() {
        try {
            String jsonGreeting = "{\"id\": 1, \"content\": \"Greeting\"}";

            Greeting readedGreeting = objectMapper.readValue(jsonGreeting, Greeting.class);

            assertThat(readedGreeting.id()).isEqualTo(1);
            assertThat(readedGreeting.content()).isEqualTo("Greeting");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void readInvalidStringOfGreeting() {
        String invalidJsonGreeting = "{\"id\": 1, \"name\": \"Greeting\"}";

        assertThrows(DatabindException.class,
                () -> objectMapper.readValue(invalidJsonGreeting, Greeting.class));
    }
}
```