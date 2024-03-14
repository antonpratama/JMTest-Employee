package com.jasamedika.techtest.service;

import com.jasamedika.techtest.entity.Pegawai;
import com.jasamedika.techtest.model.DaftarPegawaiResponse;
import com.jasamedika.techtest.model.DepartemenHRDComboBoxResponse;
import com.jasamedika.techtest.repository.PegawaiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PegawaiService {

    @Autowired
    private PegawaiRepository pegawaiRepository;

    public List<DepartemenHRDComboBoxResponse> getComboBoxDepartemenHRD(String namaDepartemen){
        List<Pegawai> pegawaiList = pegawaiRepository.findAllByNamaDepartemen(namaDepartemen);
        return pegawaiList.stream().map(pegawai -> toDepartemenHRDComboBoxResponse(pegawai)).toList();
    }

    public List<DaftarPegawaiResponse> getDaftarPegawai(){
        List<Pegawai> pegawaiList = pegawaiRepository.findAll();
        return pegawaiList.stream().map(pegawai -> toDaftarPegawaiResponse(pegawai)).toList();
    }

    private DaftarPegawaiResponse toDaftarPegawaiResponse(Pegawai pegawai){
        return DaftarPegawaiResponse
                .builder()
                .idUser(pegawai.getIdUser())
                .namaLengkap(pegawai.getNamaLengkap())
                .tempatLahir(pegawai.getTempatLahir())
                .tanggalLahir(pegawai.getTanggalLahir())
                .email(pegawai.getEmail())
                .password(pegawai.getPassword())
                .nikUser(pegawai.getNikUser())
                .kdJabatan(pegawai.getKdJabatan())
                .namaJabatan(pegawai.getNamaJabatan())
                .kdDepartemen(pegawai.getKdDepartemen())
                .namaDepartemen(pegawai.getNamaDepartemen())
                .kdUnitKerja(pegawai.getKdUnitKerja())
                .namaUnitKerja(pegawai.getNamaUnitKerja())
                .kdJenisKelamin(pegawai.getKdJenisKelamin())
                .namaJenisKelamin(pegawai.getNamaJenisKelamin())
                .kdPendidikan(pegawai.getKdPendidikan())
                .namaPendidikan(pegawai.getNamaPendidikan())
                .photo(pegawai.getPhoto())
                .build();
    }

    private DepartemenHRDComboBoxResponse toDepartemenHRDComboBoxResponse(Pegawai pegawai){
        return DepartemenHRDComboBoxResponse
                .builder()
                .namaLengkap(pegawai.getNamaLengkap())
                .kdJabatan(pegawai.getKdJabatan())
                .namaJabatan(pegawai.getNamaJabatan())
                .build();
    }
}
