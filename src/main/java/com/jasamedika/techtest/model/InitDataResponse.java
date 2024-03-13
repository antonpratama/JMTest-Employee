package com.jasamedika.techtest.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InitDataResponse {

    private String email;

    private String password;

    private String namaAdmin;

    private String perusahaan;
}
