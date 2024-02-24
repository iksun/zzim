package com.sun.zzim.controller.user;

import com.sun.zzim.service.user.IUserReader;
import com.sun.zzim.service.user.IUserSignUpExecutor;
import com.sun.zzim.service.user.UserSignUpParam;
import com.sun.zzim.service.user.auth.UserDetail;
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

    @PostMapping("/users")
    public boolean signUp(@RequestBody UserCreateRequest userCreateRequest) {
        return signUpExecutor.signup(
                new UserSignUpParam(
                        userCreateRequest.getLoginId(),
                        userCreateRequest.getPassword())
        );
    }

    @GetMapping("/users/{userId}")
    public ResponseEntity<UserResponse> getUser(@AuthenticationPrincipal UserDetail userDetail,
                                                @PathVariable Long userId) {
        if(!Objects.equals(userDetail.getUserId(), userId)) {
            return new ResponseEntity(HttpStatus.UNAUTHORIZED);
        }

        return ResponseEntity.ok(new UserResponse(userReader.getUser(userId)));
    }
}
