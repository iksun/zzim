package com.sun.zzim.repository.zzim;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "zzims")
@NoArgsConstructor
public class ZzimDataModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long userId;
    private Long zzimBoxId;
    private Long productId;

    public ZzimDataModel(Long userId, Long zzimBoxId, Long productId) {
        this.userId = userId;
        this.zzimBoxId = zzimBoxId;
        this.productId = productId;
    }
}
