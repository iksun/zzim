package com.sun.zzim.controller;

import lombok.Getter;

@Getter
public class UserLoginRequest {
    private final String loginId;
    private final String password;

    public UserLoginRequest(String loginId, String password) {
        this.loginId = loginId;
        this.password = password;
    }
}
