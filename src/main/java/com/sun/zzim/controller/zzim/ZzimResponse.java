package com.sun.zzim.controller.zzim;

import com.sun.zzim.controller.product.ProductResponse;
import lombok.Getter;

@Getter
public class ZzimResponse {
    private Long id;
    private Long userId;
    private Long zzimBoxId;
    private ProductResponse productResponse;

    public ZzimResponse(Long id, Long userId, Long zzimBoxId, ProductResponse productResponse) {
        this.id = id;
        this.userId = userId;
        this.zzimBoxId = zzimBoxId;
        this.productResponse = productResponse;
    }
}
