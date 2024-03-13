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
@Table(name = "auth")
public class Auth extends BaseEntity{

    @Id
    private String email;

    private String password;

    @Column(name = "nama_admin")
    private String namaAdmin;

    private String perusahaan;

    private String token;

    @Column(name = "token_expired_at")
    private Long tokenExpiredAt;
}
