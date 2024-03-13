package com.jasamedika.techtest.controller;

import com.jasamedika.techtest.model.DepartemenComboBoxResponse;
import com.jasamedika.techtest.model.JabatanComboBoxResponse;
import com.jasamedika.techtest.model.WebResponse;
import com.jasamedika.techtest.service.DepartemenService;
import com.jasamedika.techtest.service.JabatanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PegawaiController {

    @Autowired
    private JabatanService jabatanService;

    @Autowired
    private DepartemenService departemenService;

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
}
