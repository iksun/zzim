package com.sun.zzim.controller.user;

import com.sun.zzim.service.user.IUserReader;
import com.sun.zzim.service.user.IUserSignUpExecutor;
import com.sun.zzim.service.user.UserSignUpParam;
import com.sun.zzim.service.user.auth.UserDetail;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
public class UserController {
    private final IUserSignUpExecutor signUpExecutor;
    private final IUserReader userReader;

    public UserController(IUserSignUpExecutor signUpExecutor, IUserReader userReader) {
        this.signUpExecutor = signUpExecutor;
        this.userReader = userReader;
    }

    @Operation(summary = "회원가입", description = "회원가입을 시도합니다.")
    @PostMapping("/users")
    public boolean signUp(@RequestBody UserCreateRequest userCreateRequest) {
        return signUpExecutor.signup(
                new UserSignUpParam(
                        userCreateRequest.getLoginId(),
                        userCreateRequest.getPassword())
        );
    }

    @Operation(summary = "회원정보 조회", description = "나의 회원 정보를 조회합니다. ")
    @GetMapping("/users/{userId}")
    public ResponseEntity<UserResponse> getUser(@AuthenticationPrincipal UserDetail userDetail,
                                                @PathVariable Long userId) {
        if(!Objects.equals(userDetail.getUserId(), userId)) {
            return new ResponseEntity(HttpStatus.UNAUTHORIZED);
        }

        return ResponseEntity.ok(new UserResponse(userReader.getUser(userId)));
    }
}
