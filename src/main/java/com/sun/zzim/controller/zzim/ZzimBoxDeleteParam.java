package com.sun.zzim.controller.zzim;

import lombok.Getter;

@Getter
public class ZzimBoxDeleteParam {
    private final long userId;
    private final long boxId;

    public ZzimBoxDeleteParam(long userId, long boxId) {
        this.userId = userId;
        this.boxId = boxId;
    }

}
