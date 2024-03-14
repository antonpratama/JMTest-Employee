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
public class TambahPegawaiRequest {

    @NotBlank
    @Size(max = 100)
    private String namaLengkap;
    @NotBlank
    @Size(max = 100)
    private String email;
    @NotBlank
    @Size(max = 100)
    private String tempatLahir;
    @NotBlank
    private int tanggalLahir;
    @NotBlank
    private int kdJenisKelamin;
    @NotBlank
    private int kdPendidikan;
    @NotBlank
    private int kdJabatan;
    @NotBlank
    private int kdDepartemen;
    @NotBlank
    private int kdUnitKerja;
    @NotBlank
    @Size(max = 100)
    private String password;
    @NotBlank
    @Size(max = 100)
    private String passwordC;





}
