package com.sun.zzim.service.product;

import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class Product {
    private long id;
    private String name;
    private String thumbnail;
    private BigDecimal price;

    public Product(long id, String name, String thumbnail, BigDecimal price) {
        this.id = id;
        this.name = name;
        this.thumbnail = thumbnail;
        this.price = price;
    }
}
