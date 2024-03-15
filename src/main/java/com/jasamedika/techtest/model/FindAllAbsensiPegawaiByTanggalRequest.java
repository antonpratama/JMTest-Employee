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
public class FindAllAbsensiPegawaiByTanggalRequest {

    private int tglAwal;

    private int tglAkhir;

    @NotNull
    private Integer page;

    @NotNull
    private Integer size;
}
