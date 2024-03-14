package com.jasamedika.techtest.repository;

import com.jasamedika.techtest.entity.Pegawai;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PegawaiRepository extends JpaRepository<Pegawai, String> {

    List<Pegawai> findAllByNamaDepartemen(String namaDepartemen);
}
