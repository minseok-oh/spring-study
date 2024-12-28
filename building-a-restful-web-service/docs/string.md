# String vs StringBuffer vs StringBuilder

### Concat 성능 비교

```java
public class StringComparisonTest {

    interface Callback {
        void execute();
    }

    private void measureMethodTime(Callback callback) {
        long start = System.nanoTime();
        callback.execute();
        long end = System.nanoTime();

        System.out.println(end - start);
    }

    @Test
    public void compareStringPerformance() {
        measureMethodTime(() -> {
            String template = "Hello, ";
            for (int i = 0; i < 10000; i++) {
                template += "World";
            }
        });

        measureMethodTime(() -> {
            String template = "Hello, ";
            for (int i = 0; i < 10000; i++) {
                template.concat("World");
            }
        });

        measureMethodTime(() -> {
            StringBuffer template = new StringBuffer("Hello, ");
            for (int i = 0; i < 10000; i++) {
                template.append("World");
            }
        });

        measureMethodTime(() -> {
            StringBuilder template = new StringBuilder("Hello, ");
            for (int i = 0; i < 10000; i++) {
                template.append("World");
            }
        });
    }
}
```

| String(+)  | String(concat) | StringBuffer |StringBuilder|
|------------|----------------|--------------|-------------|
| 34080834nm | 753917nm       | 386375nm     |263459nm|


단순 읽기면 String 아니면 StringBuilder!
<br/>

### StringBuffer와 StringBuilder

```java
public class StringComparisonTest {

    @Test
    public void compareStringConcurrency() throws InterruptedException {
        StringBuffer stringBuffer = new StringBuffer();
        StringBuilder stringBuilder = new StringBuilder();

        new Thread(() -> {
            for (int i = 0; i < 10000; i++) {
                stringBuffer.append(1);
                stringBuilder.append(1);
            }
        }).start();

        new Thread(() -> {
            for (int i = 0; i < 10000; i++) {
                stringBuffer.append(1);
                stringBuilder.append(1);
            }
        }).start();

        Thread.sleep(2000);
        assertThat(stringBuffer.length()).isEqualTo(20000);
        assertThat(stringBuilder.length()).isNotEqualTo(20000);
    }
}
```

StringBuffer는 Thread 안정적이다!
<br/>

# static?

### 사용 목적
- 객체마다 달라지는 값이 아닌 공유되어 사용하는 것
- 전역적으로 객체 생성 없이 접근할 수 있는 것

### 특징
- 클래스가 메모리 로드될 때 JVM의 메서드 영역에 생성
- 하위 클래스에서 재정의 시 감춰짐
