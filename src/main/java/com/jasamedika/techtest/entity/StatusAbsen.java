package com.jasamedika.techtest.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "statusabsen")
public class StatusAbsen {

    @Id
    @Column(name = "kd_status")
    private int kdStatus;

    @Column(name = "nama_status")
    private String namaStatus;
}
