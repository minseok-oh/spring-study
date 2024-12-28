# Record

### 사용 목적
- 레코드는 Java 16에서 추가된 불변 데이터에 대한 전달 역할 클래스이다
- 생성자, getter, hashCode, toString이 들어가져 있다

### 특징
- 불변 객체로 암시적으로 `final`로 선언되어 이후 Setter, 상속이 불가능하다
- 객체의 필드는 모두 `private final`로 선언된다
- 레코드에서 관리하기 위해 `static` 변수는 생성이 가능하다
- validation을 위해 컴팩트 생성자를 사용할 수 있다