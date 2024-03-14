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
@Table(name = "pegawai")
public class Pegawai extends BaseEntity{

    @Id
    @Column(name = "id_user")
    private String idUser;

    @Column(name = "nama_lengkap")
    private String namaLengkap;

    @Column(name = "tempat_lahir")
    private String tempatLahir;

    @Column(name = "tanggal_lahir")
    private int tanggalLahir;

    private String email;

    private String password;

    @Column(name = "nik_user")
    private String nikUser;

    @Column(name = "kd_jabatan")
    private int kdJabatan;

    @Column(name = "nama_jabatan")
    private String namaJabatan;

    @Column(name = "kd_departemen")
    private int kdDepartemen;

    @Column(name = "nama_departemen")
    private String namaDepartemen;

    @Column(name = "kd_unit_kerja")
    private int kdUnitKerja;

    @Column(name = "nama_unit_kerja")
    private String namaUnitKerja;

    @Column(name = "kd_jenis_kelamin")
    private int kdJenisKelamin;

    @Column(name = "nama_jenis_kelamin")
    private String namaJenisKelamin;

    @Column(name = "kd_pendidikan")
    private int kdPendidikan;

    @Column(name = "nama_pendidikan")
    private String namaPendidikan;

    private String photo;


}
