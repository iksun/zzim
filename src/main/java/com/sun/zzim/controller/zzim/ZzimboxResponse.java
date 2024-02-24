package com.sun.zzim.controller.zzim;

import lombok.Getter;

@Getter
public class ZzimboxResponse {
    private Long id;
    private String name;
    private Long userId;

    public ZzimboxResponse(Long id, String name, Long userId) {
        this.id = id;
        this.name = name;
        this.userId = userId;
    }
}
