package com.sun.zzim.service.zzim;

import lombok.Getter;

@Getter
public class ZzimParam {
    private final Long userId;
    private final Long zzimBoxId;
    private final Long productId;

    public ZzimParam(Long userId, Long zzimBoxId, Long productId) {
        this.userId = userId;
        this.zzimBoxId = zzimBoxId;
        this.productId = productId;
    }
}
