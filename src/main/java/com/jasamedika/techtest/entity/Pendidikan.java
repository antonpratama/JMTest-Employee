package com.jasamedika.techtest.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "pendidikan")
public class Pendidikan extends BaseEntity{

    @Id
    @Column(name = "kd_pendidikan")
    private int kdPendidikan;

    @Column(name = "nama_pendidikan")
    private String namaPendidikan;
}
