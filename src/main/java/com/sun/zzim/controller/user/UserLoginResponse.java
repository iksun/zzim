package com.sun.zzim.controller.user;

import lombok.Getter;

@Getter
public class UserLoginResponse {
    private long userId;
    private String jwtToken;

    public UserLoginResponse(long userId, String jwtToken) {
        this.userId = userId;
        this.jwtToken = jwtToken;
    }
}
