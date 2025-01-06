# LDAP이란 무엇인가?

- LDAP은 사용자가 조직 등에 대한 데이터를 찾는데 도움이 되는 프로토콜이다
- 디렉토리 트리 기반으로 데이터를 저장하고 이를 사용해 권한 등을 관리한다
- NoSQL 데이터베이스 서버라고 간주할 수 있다
- LDAP 서버에서 디렉토리를 관리하며 요청마다 LDAP 서버에 권한을 확인한다

```text
dn: ou=groups,dc=springframework,dc=org
objectclass: top
objectclass: organizationalUnit
ou: groups
```