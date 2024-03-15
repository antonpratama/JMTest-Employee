package com.jasamedika.techtest.controller;

import com.jasamedika.techtest.entity.Pegawai;
import com.jasamedika.techtest.model.*;
import com.jasamedika.techtest.service.AbsensiService;
import com.jasamedika.techtest.service.StatusAbsenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AbsensiController {

    @Autowired
    private AbsensiService absensiService;

    @Autowired
    private StatusAbsenService statusAbsenService;

    @GetMapping(
            path = "/api/presensi/combo/status-absen",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<List<StatusAbsenComboBoxResponse>> getStatusComboBox(){
        List<StatusAbsenComboBoxResponse> comboBox = statusAbsenService.getComboBox();

        return WebResponse.<List<StatusAbsenComboBoxResponse>>builder().data(comboBox).build();
    }

    @GetMapping(
            path = "/api/presensi/in",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<AbsensiResponse> checkIn(Pegawai pegawai){
        AbsensiResponse absensiResponse = absensiService.checkIn(pegawai, "checkin");

        return WebResponse.<AbsensiResponse>builder().data(absensiResponse).build();
    }

    @GetMapping(
            path = "/api/presensi/out",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<AbsensiResponse> checkOut(Pegawai pegawai){
        AbsensiResponse absensiResponse = absensiService.checkIn(pegawai, "checkout");

        return WebResponse.<AbsensiResponse>builder().data(absensiResponse).build();
    }

    @GetMapping(
            path = "/api/presensi/daftar/admin",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<List<AbsensiResponse>> adminGetAbsensi(Pegawai pegawai,
                                                              @RequestParam(value = "tglAwal", required = true) Integer tglAwal,
                                                              @RequestParam(value = "tglAkhir", required = true) Integer tglAkhir,
                                                              @RequestParam(value = "page", defaultValue = "0") Integer page,
                                                              @RequestParam(value = "size", defaultValue = "10") Integer size){
        FindAllAbsensiPegawaiByTanggalRequest request = FindAllAbsensiPegawaiByTanggalRequest
                .builder()
                .page(page)
                .size(size)
                .tglAwal(tglAwal)
                .tglAkhir(tglAkhir)
                .build();

        Page<AbsensiResponse> absensiResponses = absensiService.findAllAbsensi(pegawai, request);
        return WebResponse.<List<AbsensiResponse>>builder()
                .data(absensiResponses.getContent())
                .paging(PagingResponse
                        .builder()
                        .currentPage(absensiResponses.getNumber())
                        .totalPage(absensiResponses.getTotalPages())
                        .size(absensiResponses.getSize())
                        .build())
                .build();
    }

    @GetMapping(
            path = "/api/presensi/daftar/pegawai",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<List<AbsensiResponse>> pegawaiGetAbsensi(Pegawai pegawai,
                                                              @RequestParam(value = "tglAwal", required = true) Integer tglAwal,
                                                              @RequestParam(value = "tglAkhir", required = true) Integer tglAkhir,
                                                              @RequestParam(value = "page", defaultValue = "0") Integer page,
                                                              @RequestParam(value = "size", defaultValue = "10") Integer size){
        FindAllAbsensiPegawaiByTanggalRequest request = FindAllAbsensiPegawaiByTanggalRequest
                .builder()
                .page(page)
                .size(size)
                .tglAwal(tglAwal)
                .tglAkhir(tglAkhir)
                .build();

        Page<AbsensiResponse> absensiResponses = absensiService.findAllAbsensiCurrentPegawai(pegawai, request);
        return WebResponse.<List<AbsensiResponse>>builder()
                .data(absensiResponses.getContent())
                .paging(PagingResponse
                        .builder()
                        .currentPage(absensiResponses.getNumber())
                        .totalPage(absensiResponses.getTotalPages())
                        .size(absensiResponses.getSize())
                        .build())
                .build();
    }
}