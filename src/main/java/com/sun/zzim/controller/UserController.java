package com.sun.zzim.controller;

import com.sun.zzim.service.IUserSignUpExecutor;
import com.sun.zzim.service.UserSignUpParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    private final IUserSignUpExecutor signUpExecutor;

    public UserController(IUserSignUpExecutor signUpExecutor) {
        this.signUpExecutor = signUpExecutor;
    }

    @PostMapping("/users")
    public boolean signUp(@RequestBody UserCreateRequest userCreateRequest) {
        return signUpExecutor.signup(
                new UserSignUpParam(
                        userCreateRequest.getLoginId(),
                        userCreateRequest.getPassword())
        );
    }
}
