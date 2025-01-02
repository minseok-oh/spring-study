# Multipart란?

클라이언트와 서버 간에 전송되는 HTTP 요청이나 응답에서 여러 종류의 데이터를 동시에 전송하기 위한 방식이다. <br/>
일반적으로 파일이나 이미지 등을 보낼 때 사용한다. 즉, 사용자 컴퓨터에서 서버로 파일을 보내는 것이다. <br/>

# Multipart Headers

- `Content-Type`: 콘텐츠의 타입을 작성하는데 multipart/form-data를 사용하며 각 데이터 간의 구분자도 보내준다 
  - ex) multipart/form-data; boundary=----WebKitFormBoundaryA1B2C3D4
- `Content-Length`: 콘텐츠의 총 길이를 작성하는 헤더이다
- `Content-Disposition`: 콘텐츠의 성질을 나타내는 헤더로 inline, attachment 등의 값이 들어간다

# Multipart Inner

```text
------WebKitFormBoundaryA1B2C3D4
Content-Disposition: form-data; name="username"

john_doe
------WebKitFormBoundaryA1B2C3D4
Content-Disposition: form-data; name="profile_picture"; filename="profile.jpg"
Content-Type: image/jpeg

(binary data here)
------WebKitFormBoundaryA1B2C3D4--
```

예시 데이터를 확인하면 위와 같다. 구분자를 통해 각 데이터가 분리되어 전달된다. <br/>
맨 마지막에 대한 구분자에 대해서는 `--`가 들어가며 마무리된다.