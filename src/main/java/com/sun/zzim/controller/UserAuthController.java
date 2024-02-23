package com.sun.zzim.controller;

import com.sun.zzim.service.auth.IUserLoginExecutor;
import com.sun.zzim.service.auth.UserLoginParam;
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
    public ResponseEntity<String> login(@RequestBody UserLoginRequest userLoginRequest) {
        return ResponseEntity.ok(
                userLoginExecutor.login(
                        new UserLoginParam(
                                userLoginRequest.getLoginId(),
                                userLoginRequest.getPassword())
                )
        );
    }
}
