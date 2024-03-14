package com.jasamedika.techtest.service;

import com.jasamedika.techtest.entity.Pegawai;
import com.jasamedika.techtest.model.*;
import com.jasamedika.techtest.repository.PegawaiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@Service
public class PegawaiService {

    @Autowired
    private PegawaiRepository pegawaiRepository;

    public List<DepartemenHRDComboBoxResponse> getComboBoxDepartemenHRD(String namaDepartemen){
        List<Pegawai> pegawaiList = pegawaiRepository.findAllByNamaDepartemen(namaDepartemen);
        return pegawaiList.stream().map(pegawai -> toDepartemenHRDComboBoxResponse(pegawai)).toList();
    }

    public List<DaftarPegawaiResponse> getDaftarPegawai(Pegawai pegawai){
        Pegawai currentPegawai = pegawaiRepository.findById(pegawai.getIdUser())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Pegawai " + pegawai.getIdUser() + " tidak dikenal"));

        if (!currentPegawai.getNamaJabatan().equals("admin") || !currentPegawai.getNamaDepartemen().equals("hrd")) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Hanya admin/pegawai HRD yang bisa menggunakan fitur ini");
        }

        List<Pegawai> pegawaiList = pegawaiRepository.findAll();
        return pegawaiList.stream().map(crPegawai -> toDaftarPegawaiResponse(crPegawai)).toList();
    }

    public PegawaiResponse addPegawai(Pegawai pegawai, TambahPegawaiRequest request){
        Pegawai currentPegawai = pegawaiRepository.findById(pegawai.getIdUser())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Pegawai " + pegawai.getIdUser() + " tidak dikenal"));

        if (!currentPegawai.getNamaJabatan().equals("admin") || !currentPegawai.getNamaDepartemen().equals("hrd")) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Hanya admin/pegawai HRD yang bisa menggunakan fitur ini");
        }

        if (request.getPassword().equals(request.getPasswordC())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Password dan confirm password tidak cocok");
        }

        Pegawai newPegawai = new Pegawai();
        newPegawai.setIdUser(UUID.randomUUID().toString());
        newPegawai.setNamaLengkap(request.getNamaLengkap());
        newPegawai.setEmail(request.getEmail());
        newPegawai.setTempatLahir(request.getTempatLahir());
        newPegawai.setTanggalLahir(request.getTanggalLahir());
        newPegawai.setKdJenisKelamin(request.getKdJenisKelamin());
        newPegawai.setKdPendidikan(request.getKdPendidikan());
        newPegawai.setKdJabatan(request.getKdJabatan());
        newPegawai.setKdUnitKerja(request.getKdUnitKerja());
        newPegawai.setPassword(request.getPassword());

        pegawaiRepository.save(newPegawai);

        return toPegawaiResponse(newPegawai);
    }

    public PegawaiResponse updatePegawai(Pegawai pegawai, UbahPegawaiRequest request){
        Pegawai currentPegawai = pegawaiRepository.findById(pegawai.getIdUser())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Pegawai " + pegawai.getIdUser() + " tidak dikenal"));

        if (!currentPegawai.getNamaJabatan().equals("admin") || !currentPegawai.getNamaDepartemen().equals("hrd")) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Hanya admin/pegawai HRD yang bisa menggunakan fitur ini");
        }


        Pegawai reqPegawai = pegawaiRepository.findById(request.getIdUser())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Pegawai " + pegawai.getIdUser() + " tidak dikenal"));

        if (request.getPassword().equals(request.getPasswordC())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Password dan confirm password tidak cocok");
        }

        reqPegawai.setNamaLengkap(request.getNamaLengkap());
        reqPegawai.setEmail(request.getEmail());
        reqPegawai.setTempatLahir(request.getTempatLahir());
        reqPegawai.setTanggalLahir(request.getTanggalLahir());
        reqPegawai.setKdJenisKelamin(request.getKdJenisKelamin());
        reqPegawai.setKdPendidikan(request.getKdPendidikan());
        reqPegawai.setKdJabatan(request.getKdJabatan());
        reqPegawai.setKdUnitKerja(request.getKdUnitKerja());
        reqPegawai.setPassword(request.getPassword());

        pegawaiRepository.save(reqPegawai);

        return toPegawaiResponse(reqPegawai);
    }

    public PegawaiResponse adminUpdatePhoto(Pegawai pegawai, AdminUpdatePhotoPegawaiRequest request){
        Pegawai currentPegawai = pegawaiRepository.findById(pegawai.getIdUser())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Pegawai " + pegawai.getIdUser() + " tidak dikenal"));

        if (!currentPegawai.getNamaJabatan().equals("admin") || !currentPegawai.getNamaDepartemen().equals("hrd")) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Hanya admin/pegawai HRD yang bisa menggunakan fitur ini");
        }

        Pegawai reqPegawai = pegawaiRepository.findById(request.getIdUser())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Pegawai " + pegawai.getIdUser() + " tidak dikenal"));

        reqPegawai.setPhoto(request.getFiles());

        pegawaiRepository.save(reqPegawai);

        return toPegawaiResponse(reqPegawai);
    }

    public PegawaiResponse updatePhoto(Pegawai pegawai, UpdatePhotoPegawaiRequest request){
        Pegawai currentPegawai = pegawaiRepository.findById(pegawai.getIdUser())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Pegawai " + pegawai.getIdUser() + " tidak dikenal"));

        currentPegawai.setPhoto(request.getFiles());

        pegawaiRepository.save(currentPegawai);

        return toPegawaiResponse(currentPegawai);
    }

    private PegawaiResponse toPegawaiResponse(Pegawai pegawai){
        return PegawaiResponse
                .builder()
                .namaLengkap(pegawai.getNamaLengkap())
                .email(pegawai.getEmail())
                .tempatLahir(pegawai.getTempatLahir())
                .tanggalLahir(pegawai.getTanggalLahir())
                .kdJenisKelamin(pegawai.getKdJenisKelamin())
                .kdPendidikan(pegawai.getKdPendidikan())
                .kdJabatan(pegawai.getKdJabatan())
                .kdDepartemen(pegawai.getKdDepartemen())
                .kdUnitKerja(pegawai.getKdUnitKerja())
                .build();
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
