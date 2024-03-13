package com.jasamedika.techtest.service;

import com.jasamedika.techtest.entity.Auth;
import com.jasamedika.techtest.entity.Jabatan;
import com.jasamedika.techtest.model.JabatanComboBoxResponse;
import com.jasamedika.techtest.repository.AuthRepository;
import com.jasamedika.techtest.repository.JabatanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class JabatanService {

    @Autowired
    private JabatanRepository jabatanRepository;

    @Autowired
    private AuthRepository authRepository;

    public List<JabatanComboBoxResponse> getComboBox(){
//        authRepository.findById(auth.getEmail())
//                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));

        List<Jabatan> listJabatan = jabatanRepository.findAll();
        return listJabatan.stream().map(jabatan -> toJabatanComboBoxResponse(jabatan)).toList();
    }

    private JabatanComboBoxResponse toJabatanComboBoxResponse(Jabatan jabatan){
        return JabatanComboBoxResponse.builder()
                .kdJabatan(jabatan.getKdJabatan())
                .namaJabatan(jabatan.getNamaJabatan())
                .build();
    }
}
