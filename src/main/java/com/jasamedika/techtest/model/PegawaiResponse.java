package com.jasamedika.techtest.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PegawaiResponse {

    private String namaLengkap;

    private String email;

    private String tempatLahir;

    private int tanggalLahir;

    private int kdJenisKelamin;

    private int kdPendidikan;

    private int kdJabatan;

    private int kdDepartemen;

    private int kdUnitKerja;

}
