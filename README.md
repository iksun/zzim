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