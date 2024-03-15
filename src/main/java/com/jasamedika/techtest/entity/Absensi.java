package com.jasamedika.techtest.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "absensi")
public class Absensi extends BaseEntity{

//    @Column(name = "id_user")
//    private int idUser;
//
//    @Column(name = "nama_lengkap")
//    private String namaLengkap;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "tgl_absensi")
    private int tglAbsensi;

    @Column(name = "jam_masuk")
    private String jamMasuk;

    @Column(name = "jam_keluar")
    private String jamKeluar;

    @Column(name = "nama_status")
    private String namaStatus;

    @ManyToOne
    @JoinColumn(name = "id_user", referencedColumnName = "id_user")
    private Pegawai pegawai;
}
