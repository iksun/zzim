package com.sun.zzim.controller.user;

import com.sun.zzim.service.user.User;
import lombok.Getter;

@Getter
public class UserResponse {
    private long id;
    private String loginId;

    public UserResponse(User user) {
        this.id = user.getId();
        this.loginId = user.getLoginId();
    }
}
