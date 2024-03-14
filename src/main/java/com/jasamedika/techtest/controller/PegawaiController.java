package com.jasamedika.techtest.controller;

import com.jasamedika.techtest.entity.Pegawai;
import com.jasamedika.techtest.model.*;
import com.jasamedika.techtest.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PegawaiController {

    @Autowired
    private JabatanService jabatanService;

    @Autowired
    private DepartemenService departemenService;

    @Autowired
    private UnitKerjaService unitKerjaService;

    @Autowired
    private PendidikanService pendidikanService;

    @Autowired
    private JenisKelaminService jenisKelaminService;

    @Autowired
    private PegawaiService pegawaiService;

    @GetMapping(
            path = "/api/pegawai/combo/jabatan",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<List<JabatanComboBoxResponse>> getJabatanComboBox(){
        List<JabatanComboBoxResponse> jabatanServiceComboBox = jabatanService.getComboBox();

        return WebResponse.<List<JabatanComboBoxResponse>>builder().data(jabatanServiceComboBox).build();
    }

    @GetMapping(
            path = "/api/pegawai/combo/departemen",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<List<DepartemenComboBoxResponse>> getDepartemenComboBox(){
        List<DepartemenComboBoxResponse> comboBoxResponses = departemenService.getComboBox();

        return WebResponse.<List<DepartemenComboBoxResponse>>builder().data(comboBoxResponses).build();
    }

    @GetMapping(
            path = "/api/pegawai/combo/unit-kerja",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<List<UnitKerjaComboBoxResponse>> getUnitKerjaComboBox(){
        List<UnitKerjaComboBoxResponse> comboBoxResponses = unitKerjaService.getComboBox();

        return WebResponse.<List<UnitKerjaComboBoxResponse>>builder().data(comboBoxResponses).build();
    }

    @GetMapping(
            path = "/api/pegawai/combo/pendidikan",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<List<PendidikanComboBoxResponse>> getPendidikanComboBox(){
        List<PendidikanComboBoxResponse> comboBoxResponses = pendidikanService.getComboBox();

        return WebResponse.<List<PendidikanComboBoxResponse>>builder().data(comboBoxResponses).build();
    }

    @GetMapping(
            path = "/api/pegawai/combo/jenis-kelamin",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<List<JenisKelaminComboBoxResponse>> getJenisKelaminComboBox(){
        List<JenisKelaminComboBoxResponse> comboBoxResponses = jenisKelaminService.getComboBox();

        return WebResponse.<List<JenisKelaminComboBoxResponse>>builder().data(comboBoxResponses).build();
    }

    @GetMapping(
            path = "/api/pegawai/combo/departemen-hrd",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<List<DepartemenHRDComboBoxResponse>> getDepartemenHRDComboBox(){
        List<DepartemenHRDComboBoxResponse> comboBoxResponses = pegawaiService.getComboBoxDepartemenHRD("hrd");

        return WebResponse.<List<DepartemenHRDComboBoxResponse>>builder().data(comboBoxResponses).build();
    }

    @GetMapping(
            path = "/api/pegawai/daftar",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<List<DaftarPegawaiResponse>> getDaftarPegawai(Pegawai pegawai){
        List<DaftarPegawaiResponse> comboBoxResponses = pegawaiService.getDaftarPegawai(pegawai);

        return WebResponse.<List<DaftarPegawaiResponse>>builder().data(comboBoxResponses).build();
    }

    @PostMapping(
            path = "/api/pegawai/admin-tambah-pegawai",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<PegawaiResponse> addPegawai(Pegawai pegawai,
                                                   @RequestBody TambahPegawaiRequest request){
        PegawaiResponse pegawaiResponse = pegawaiService.addPegawai(pegawai, request);
        return WebResponse.<PegawaiResponse>builder().data(pegawaiResponse).build();
    }

    @PostMapping(
            path = "/api/pegawai/admin-ubah-pegawai",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<PegawaiResponse> updatePegawai(Pegawai pegawai,
                                                   @RequestBody UbahPegawaiRequest request){
        PegawaiResponse pegawaiResponse = pegawaiService.updatePegawai(pegawai, request);
        return WebResponse.<PegawaiResponse>builder().data(pegawaiResponse).build();
    }

    @PostMapping(
            path = "/api/pegawai/admin-ubah-photo",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<PegawaiResponse> adminUpdatePhoto(Pegawai pegawai,
                                                      @RequestBody AdminUpdatePhotoPegawaiRequest request){
        PegawaiResponse pegawaiResponse = pegawaiService.adminUpdatePhoto(pegawai, request);
        return WebResponse.<PegawaiResponse>builder().data(pegawaiResponse).build();
    }

    @PostMapping(
            path = "/api/pegawai/ubah-photo",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<PegawaiResponse> updatePhoto(Pegawai pegawai,
                                                         @RequestBody UpdatePhotoPegawaiRequest request){
        PegawaiResponse pegawaiResponse = pegawaiService.updatePhoto(pegawai, request);
        return WebResponse.<PegawaiResponse>builder().data(pegawaiResponse).build();
    }
}
