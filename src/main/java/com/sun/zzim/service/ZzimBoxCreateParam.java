package com.sun.zzim.service;

import lombok.Getter;

@Getter
public class ZzimBoxCreateParam {
    private long userId;
    private String name;

    public ZzimBoxCreateParam(long userId, String name) {
        this.userId = userId;
        this.name = name;
    }
}
