package com.jasamedika.techtest.model;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AbsensiResponse {

    private String idUser;

    private String namaLengkap;

    private int tglAbsensi;

    private String jamMasuk;

    private String jamKeluar;

    private String namaStatus;
}
