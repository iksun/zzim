package com.sun.zzim.service.zzim;

import lombok.Getter;

@Getter
public class ZzimDeleteParam {
    private final Long boxId;
    private final Long zzimId;
    private final Long userId;

    public ZzimDeleteParam(Long boxId, Long zzimId, Long userId) {
        this.boxId = boxId;
        this.zzimId = zzimId;
        this.userId = userId;
    }
}
