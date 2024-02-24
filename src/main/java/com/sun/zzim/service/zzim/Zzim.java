package com.sun.zzim.service.zzim;

import com.sun.zzim.service.product.Product;
import lombok.Getter;

@Getter
public class Zzim {
    private final Long id;
    private final Long userId;
    private final Long zzimBoxId;
    private final Product product;

    public Zzim(Long id, Long userId, Long zzimBoxId, Product product) {
        this.id = id;
        this.userId = userId;
        this.zzimBoxId = zzimBoxId;
        this.product = product;
    }
}
