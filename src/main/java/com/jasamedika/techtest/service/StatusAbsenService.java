package com.jasamedika.techtest.service;

import com.jasamedika.techtest.entity.Departemen;
import com.jasamedika.techtest.entity.StatusAbsen;
import com.jasamedika.techtest.model.DepartemenComboBoxResponse;
import com.jasamedika.techtest.model.StatusAbsenComboBoxResponse;
import com.jasamedika.techtest.repository.DepartemenRepository;
import com.jasamedika.techtest.repository.StatusAbsenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StatusAbsenService {

    @Autowired
    private StatusAbsenRepository statusAbsenRepository;

    public List<StatusAbsenComboBoxResponse> getComboBox(){
        List<StatusAbsen> listStatusAbsen = statusAbsenRepository.findAll();
        return listStatusAbsen.stream().map(statusAbsen -> toStatusAbsenComboBoxResponse(statusAbsen)).toList();
    }

    private StatusAbsenComboBoxResponse toStatusAbsenComboBoxResponse(StatusAbsen statusAbsen){
        return StatusAbsenComboBoxResponse.builder()
                .kdStatus(statusAbsen.getKdStatus())
                .namaStatus(statusAbsen.getNamaStatus())
                .build();
    }
}
