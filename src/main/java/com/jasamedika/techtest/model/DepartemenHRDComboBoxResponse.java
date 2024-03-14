package com.jasamedika.techtest.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DepartemenHRDComboBoxResponse {

    private String namaLengkap;

    private int kdJabatan;

    private String namaJabatan;
}
