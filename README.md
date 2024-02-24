# 프로젝트 실행 가이드

1. **프로젝트 압축 파일 전송 압출을 풉니다**
   - zip 파일을 풉니다.

2. **프로젝트 폴더로 이동합니다.:**
   ```bash
   cd ../zzim
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
3. POST /users/login을 통하여 로그인하고 토큰을 발급받습니다. 
4. Authorize에 입력 후 나머지 api를 호출합니다. 
