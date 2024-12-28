# Atomic Long?

```java
private volatile long value;
```

- volatile 키워드를 사용해서 변수 접근 시 메모리 직접 참조

```java
@IntrinsicCandidate
public final native boolean compareAndSetLong(Object o, long offset,
                                              long expected,
                                              long x);
```
```cpp
UNSAFE_ENTRY(jboolean, Unsafe_CompareAndSetLong(JNIEnv *env, jobject unsafe, jobject obj, jlong offset, jlong e, jlong x)) {
  oop p = JNIHandles::resolve(obj);
  volatile jlong* addr = (volatile jlong*)index_oop_from_field_offset_long(p, offset);
  return Atomic::cmpxchg(addr, e, x) == e;
} UNSAFE_END
```

- Unsafe 라이브러리를 사용해 원자성을 보장함! (위험함)
- `@IntrinsicCandidate`로 자주 사용할테니 JVM에 네이티브로 변환할 것을 요청
- `native` 키워드를 통해 JNI로 소통하여 원자적인 C++ 코드를 실행