# @JsonIgnoreProperties 동작 원리

- Jackson이 관리하여 무시할 속성들을 설정할 수 있다
- allowGetters: Getter를 사용할 수 있도록 활성화하는 속성
- allowSetters: Setter를 사용할 수 있도록 활성화하는 속성
- ignoreUnknown: 역직렬화 중에 인식할 수 없는 속성을 무시해도 되는지 정의하는 속성
- value: 무시할 속성의 이름