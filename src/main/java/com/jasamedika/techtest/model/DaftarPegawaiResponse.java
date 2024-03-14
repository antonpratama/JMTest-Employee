package com.jasamedika.techtest.model;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DaftarPegawaiResponse {

    private String idUser;

    private String namaLengkap;

    private String tempatLahir;

    private int tanggalLahir;

    private String email;

    private String password;

    private String nikUser;

    private int kdJabatan;

    private String namaJabatan;

    private int kdDepartemen;

    private String namaDepartemen;

    private int kdUnitKerja;

    private String namaUnitKerja;

    private int kdJenisKelamin;

    private String namaJenisKelamin;

    private int kdPendidikan;

    private String namaPendidikan;

    private String photo;
}
