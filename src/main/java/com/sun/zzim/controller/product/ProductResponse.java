package com.sun.zzim.controller.product;

import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class ProductResponse {
    private long id;
    private String name;
    private String thumbnail;
    private BigDecimal price;

    public ProductResponse(long id, String name, String thumbnail, BigDecimal price) {
        this.id = id;
        this.name = name;
        this.thumbnail = thumbnail;
        this.price = price;
    }
}
