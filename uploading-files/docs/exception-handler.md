# @ControllerAdvice, @RestControllerAdvice

```java
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(StorageFileNotFoundException.class)
    public ResponseEntity<?> handleStorageFileNotFound(StorageFileNotFoundException exc) {
        return ResponseEntity.notFound().build();
    }
}
```

예외들에 대해서 try-catch를 사용하여 각각 처리해도 가능은 하다. <br/>
그러나 예외를 던지고 전체적으로 이를 관리해 줄 Handler를 구현하는 것이 더 편하다. <br/>
그래서 위처럼 `@ControllerAdvice`나 `@RestControllerAdvice` 등으로 구현하면 된다!