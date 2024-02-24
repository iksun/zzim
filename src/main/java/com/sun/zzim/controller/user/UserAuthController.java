package com.sun.zzim.controller.user;

import com.sun.zzim.service.user.auth.IUserLoginExecutor;
import com.sun.zzim.service.user.auth.UserLoginParam;
import com.sun.zzim.service.user.auth.UserLoginResult;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserAuthController {
    private final IUserLoginExecutor userLoginExecutor;

    public UserAuthController(IUserLoginExecutor userLoginExecutor) {
        this.userLoginExecutor = userLoginExecutor;
    }

    @Operation(summary = "로그인", description = "가입된 계정으로 로그인을 시도합니다.")
    @PostMapping("/users/login")
    public ResponseEntity<UserLoginResponse> login(@RequestBody UserLoginRequest userLoginRequest) {
        UserLoginResult loginResult = userLoginExecutor.login(
                new UserLoginParam(
                        userLoginRequest.getLoginId(),
                        userLoginRequest.getPassword())
        );

        if(loginResult.isSuccess()) {
            return ResponseEntity.ok(new UserLoginResponse(loginResult.getUserId(), loginResult.getJwtToken()));
        }
        return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
