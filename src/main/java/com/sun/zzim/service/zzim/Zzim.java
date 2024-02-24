package com.sun.zzim.service.zzim;

import lombok.Getter;

@Getter
public class Zzim {
    private final Long id;
    private final Long userId;
    private final Long zzimBoxId;
    private final Long productId;

    public Zzim(Long id, Long userId, Long zzimBoxId, Long productId) {
        this.id = id;
        this.userId = userId;
        this.zzimBoxId = zzimBoxId;
        this.productId = productId;
    }
}
