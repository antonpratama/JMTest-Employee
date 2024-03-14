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
public class UpdatePhotoPegawaiRequest {

    @NotBlank
    @Size(max = 100)
    private String namaFile;

    @NotBlank
    private String files;
}
