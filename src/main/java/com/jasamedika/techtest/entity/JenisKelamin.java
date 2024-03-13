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
@Table(name = "jeniskelamin")
public class JenisKelamin extends BaseEntity {

    @Id
    @Column(name = "kd_jenis_kelamin")
    private int kdJenisKelamin;

    @Column(name = "nama_jenis_kelamin")
    private String namaJenisKelamin;
}
