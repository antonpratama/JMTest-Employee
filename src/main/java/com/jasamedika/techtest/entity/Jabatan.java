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
@Table(name = "jabatan")
public class Jabatan extends BaseEntity{

    @Id
    @Column(name = "kd_jabatan")
    private int kdJabatan;

    @Column(name = "nama_jabatan")
    private String namaJabatan;
}
