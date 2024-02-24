package com.sun.zzim.service.user.auth;

import lombok.Getter;

@Getter
public class UserLoginResult {
    private boolean isSuccess;
    private long userId;
    private String jwtToken;

    public UserLoginResult(boolean isSuccess, long userId, String jwtToken) {
        this.isSuccess = isSuccess;
        this.userId = userId;
        this.jwtToken = jwtToken;
    }
}
