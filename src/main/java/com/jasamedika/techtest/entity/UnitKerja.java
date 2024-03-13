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
@Table(name = "unitkerja")
public class UnitKerja extends BaseEntity{
    @Id
    @Column(name = "kd_unit_kerja")
    private int kdUnitKerja;

    @Column(name = "nama_unit_kerja")
    private String namaUnitKerja;


}
