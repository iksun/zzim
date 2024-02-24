package com.sun.zzim.controller;

import com.sun.zzim.service.auth.IUserLoginExecutor;
import com.sun.zzim.service.auth.UserLoginParam;
import com.sun.zzim.service.auth.UserLoginResult;
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
