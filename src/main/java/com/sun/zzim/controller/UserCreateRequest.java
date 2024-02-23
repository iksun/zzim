package com.sun.zzim.controller;

import lombok.Getter;

@Getter
public class UserCreateRequest {
    private String loginId;
    private String password;

    public UserCreateRequest(String loginId, String password) {
        this.loginId = loginId;
        this.password = password;
    }
}
