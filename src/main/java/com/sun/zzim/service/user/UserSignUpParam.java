package com.sun.zzim.service.user;

import lombok.Getter;

@Getter
public class UserSignUpParam {
    private String loginId;
    private String loginPw;

    public UserSignUpParam(String loginId, String loginPw) {
        this.loginId = loginId;
        this.loginPw = loginPw;
    }
}
