package com.sun.zzim.repository.product.datamodel;

import jakarta.persistence.*;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@Entity
@Table(name = "products")
public class ProductDataModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String name;
    private String thumbnail;
    private BigDecimal price;

}
