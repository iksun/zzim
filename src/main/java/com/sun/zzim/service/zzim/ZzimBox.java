package com.sun.zzim.service.zzim;

import lombok.Getter;

@Getter
public class ZzimBox {
    private Long id;
    private String name;
    private Long userId;

    public ZzimBox(Long id, String name, Long userId) {
        this.id = id;
        this.name = name;
        this.userId = userId;
    }
}
