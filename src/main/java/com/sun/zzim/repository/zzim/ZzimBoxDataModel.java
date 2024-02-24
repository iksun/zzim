package com.sun.zzim.repository.zzim;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "zzim_boxes")
@NoArgsConstructor
public class ZzimBoxDataModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Long userId;

    public ZzimBoxDataModel(String name, Long userId) {
        this.name = name;
        this.userId = userId;
    }
}
