
# A-bly 사전 과제
- 사용기술 :java , Spring, layered-architecture
- 도메인 간 의존성 제거를 위하여 생성자 주입 DI로 처리하였습니다. 
- 중요 로직에 Unit Test를 추가하였습니다. 
- 코드 커밋 단위를 최소화 하였습니다.(실제 작업시 조금 더 작게 합니다)
- AWS free tier Mysql을 사용하여 api 속도가 느립니다. :(
- 포트 정보 : 8080


# 프로젝트 실행 가이드

1. **프로젝트 압축 파일 전송 압출을 풉니다**
    ```bash
      unzip zzim.zip
    ```


2. **프로젝트 폴더로 이동합니다.**
   ```bash
   cd ./zzim
    ```

3. ** 프로젝트를 빌드합니댜. **
    ```bash
   ./gradlew clean build
   ``` 
4. ** jar 파일을 실행합니다.**
    ```
    java -jar build/libs/zzim-0.0.1-SNAPSHOT.jar 
    ```


# api 문서
```agsl
    http://localhost:8080/swagger-ui/index.html#/ 
```
# 테스트 방법
1. api 문서에 접속합니다.
2. POST /users api를 통하여 회원 가입을 시도합니다.
    ```json 
   {"loginId": "testId3","password": "password2"}
   ```
3. POST /users/login을 통하여 로그인하고 토큰을 발급받습니다. 
   ```json 
   {"loginId": "testId3","password": "password2"}
   ```
4. 로그인 리스폰스의 **jwtToken** 값을 Authorize에 입력 후 나머지 api를 호출합니다. 


# Test case
- 유저는 회원가입을 할 수 있다. 
- 유저는 로그인을 할 수 있다.
  - 유저는 ID가 존재하지 않으면 로그인 할 수 없다.
  - 유저는 Password가 맞지않으면 로그인 할 수 없다. 
- 유저는 자기 정보를 확인할 수 있다.

- 유저는 내 서랍을 생성할 수 있다.
- 유저는 내 서랍을 삭제할 수 있다.
- 유저는 내 서랍 목록을 볼 수 있다. 
  - 유저는 내 서랍 목록을 스크롤 할 수 있다.

- 유저는 상품 목록을 볼 수 있다. 
- 유저는 상품을 찜할 수 있다.
  - 해당 상품이 이미 찜 되어있으면 찜할 수 없다. 
  - 찜서랍이 없으면 찜할 수 없다. 
- 유저는 상품을 찜 해제 할 수 있다. 
- 유저는 찜서랍 내의 찜 목록을 볼 수 있다. 
