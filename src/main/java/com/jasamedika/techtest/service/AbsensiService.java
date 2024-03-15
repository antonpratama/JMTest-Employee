package com.jasamedika.techtest.service;

import com.jasamedika.techtest.entity.Absensi;
import com.jasamedika.techtest.entity.Pegawai;
import com.jasamedika.techtest.model.AbsensiResponse;
import com.jasamedika.techtest.model.FindAllAbsensiPegawaiByTanggalRequest;
import com.jasamedika.techtest.repository.AbsensiRepository;
import com.jasamedika.techtest.repository.PegawaiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import jakarta.persistence.criteria.Predicate;
import org.springframework.web.server.ResponseStatusException;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class AbsensiService {

    @Autowired
    private AbsensiRepository absensiRepository;

    @Autowired
    private PegawaiRepository pegawaiRepository;

    public Page<AbsensiResponse> findAllAbsensi(Pegawai pegawai, FindAllAbsensiPegawaiByTanggalRequest request) {
        Pegawai currentPegawai = pegawaiRepository.findById(pegawai.getIdUser())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Pegawai " + pegawai.getIdUser() + " tidak dikenal"));

        if (!currentPegawai.getNamaJabatan().equals("admin") || !currentPegawai.getNamaDepartemen().equals("hrd")) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Hanya admin/pegawai HRD yang bisa menggunakan fitur ini");
        }


        Specification<Absensi> specification = (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (Objects.nonNull(request.getTglAwal())){
                predicates.add(criteriaBuilder.or(
                   criteriaBuilder.greaterThanOrEqualTo(root.get("tgl_absensi"), request.getTglAwal())
                ));
            }

            if (Objects.nonNull(request.getTglAkhir())){
                predicates.add(criteriaBuilder.or(
                        criteriaBuilder.lessThanOrEqualTo(root.get("tgl_absensi"), request.getTglAkhir())
                ));
            }
            return query.where(predicates.toArray(new Predicate[]{})).getRestriction();
        };

        Pageable pageable = PageRequest.of(request.getPage(), request.getSize());
        Page<Absensi> absensis = absensiRepository.findAll(specification, pageable);
        List<AbsensiResponse> absensiResponseList = absensis.getContent().stream()
                .map(absensi -> toAbsensiResponse(absensi))
                .collect(Collectors.toList());

        return new PageImpl<>(absensiResponseList, pageable, absensis.getTotalElements());
    }

    public Page<AbsensiResponse> findAllAbsensiCurrentPegawai(Pegawai pegawai, FindAllAbsensiPegawaiByTanggalRequest request) {
        Pegawai currentPegawai = pegawaiRepository.findById(pegawai.getIdUser())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Pegawai " + pegawai.getIdUser() + " tidak dikenal"));

        Specification<Absensi> specification = (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            predicates.add(criteriaBuilder.equal(root.get("pegawai"), pegawai));

            if (Objects.nonNull(request.getTglAwal())){
                predicates.add(criteriaBuilder.or(
                        criteriaBuilder.greaterThanOrEqualTo(root.get("tgl_absensi"), request.getTglAwal())
                ));
            }

            if (Objects.nonNull(request.getTglAkhir())){
                predicates.add(criteriaBuilder.or(
                        criteriaBuilder.lessThanOrEqualTo(root.get("tgl_absensi"), request.getTglAkhir())
                ));
            }
            return query.where(predicates.toArray(new Predicate[]{})).getRestriction();
        };

        Pageable pageable = PageRequest.of(request.getPage(), request.getSize());
        Page<Absensi> absensis = absensiRepository.findAll(specification, pageable);
        List<AbsensiResponse> absensiResponseList = absensis.getContent().stream()
                .map(absensi -> toAbsensiResponse(absensi))
                .collect(Collectors.toList());

        return new PageImpl<>(absensiResponseList, pageable, absensis.getTotalElements());
    }

    public AbsensiResponse checkIn(Pegawai pegawai, String status){
        Pegawai currentPegawai = pegawaiRepository.findById(pegawai.getIdUser())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Pegawai " + pegawai.getIdUser() + " tidak dikenal"));

        LocalDate today = LocalDate.now();
        Instant instant = today.atStartOfDay().toInstant(ZoneOffset.of("Asia/Jakarta"));
        int currentDate = (int)instant.getEpochSecond();
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());

        Absensi absensi = new Absensi();
        absensi.setTglAbsensi(currentDate);
        absensi.setNamaStatus(status);
        absensi.setJamMasuk(timestamp.toString());
        absensi.setPegawai(pegawai);
        absensiRepository.save(absensi);

        return toAbsensiResponse(absensi);
    }

    private AbsensiResponse toAbsensiResponse(Absensi absensi){
        return AbsensiResponse
                .builder()
                .idUser(absensi.getPegawai().getIdUser())
                .namaLengkap(absensi.getPegawai().getNamaLengkap())
                .tglAbsensi(absensi.getTglAbsensi())
                .jamMasuk(absensi.getJamMasuk())
                .jamKeluar(absensi.getJamKeluar())
                .namaStatus(absensi.getNamaStatus())
                .build();
    }
}
