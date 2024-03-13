package com.jasamedika.techtest.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class JenisKelaminComboBoxResponse {

    private int kdJenisKelamin;

    private String namaJenisKelamin;
}
