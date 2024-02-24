package com.sun.zzim.service.user;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class User {
    private long id;
    private String loginId;
    private String password;
    private LocalDateTime createTime;

    public User(long id, String loginId, String password, LocalDateTime createTime) {
        this.id = id;
        this.loginId = loginId;
        this.password = password;
        this.createTime = createTime;
    }
}
