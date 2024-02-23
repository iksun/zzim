package com.sun.zzim.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@Table(name = "users")
@NoArgsConstructor
public class UserDataModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String loginId;
    private String loginPw;
    @Column(insertable = false, updatable = false)
    private LocalDateTime createTime;

    public UserDataModel(String loginId, String loginPw) {
        this.loginId = loginId;
        this.loginPw = loginPw;
    }
}
