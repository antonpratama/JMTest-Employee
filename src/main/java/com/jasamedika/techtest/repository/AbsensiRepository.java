package com.jasamedika.techtest.repository;

import com.jasamedika.techtest.entity.Absensi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AbsensiRepository extends JpaRepository<Absensi,Long>, JpaSpecificationExecutor<Absensi>{

//    @Query("select ")
//    List<Absensi> findAllAbsensiWithPegawai();
}
