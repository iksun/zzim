package com.sun.zzim.repository.zzim;

import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
@Table(name = "zzim_boxes")
public class ZzimBoxDataModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private Long userId;
}
