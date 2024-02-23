package com.sun.zzim.service.auth;

import lombok.Getter;

@Getter
public class UserLoginParam {
    private final String loginId;
    private final String password;

    public UserLoginParam(String loginId, String password) {
        this.loginId = loginId;
        this.password = password;
    }
}
