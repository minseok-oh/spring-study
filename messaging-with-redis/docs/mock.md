# mock()

```java
RedissonClient redissonClient = mock(RedissonClient.class);
```

- mock(): Mock 객체를 생성한다. Mock 객체는 실제 객체와 같은 행동을 하지만, 실제 객체가 아닌 가짜 객체이다.
- spy(): 실제 객체를 생성하고, 일부 메소드만 Mocking 하고 싶을 때 사용한다.

# when(), thenReturn()

```java
when(redissonClient.getTopic("chat")).thenReturn(mock(RTopic.class));
```

- when(): Mock 객체의 메소드 호출 시, 반환값을 지정한다.
- thenReturn(): Mock 객체의 메소드 호출 시, 반환값을 지정한다.
- thenThrow(): Mock 객체의 메소드 호출 시, 예외를 발생시킨다.

# verify()

```java
verify(topic).publish(message);
```

- verify(): Mock 객체의 메소드 호출을 검증한다. 호출 여부, 호출 횟수, 호출 순서 등을 검증할 수 있다.
