package com.jasamedika.techtest.service;

import com.jasamedika.techtest.entity.JenisKelamin;
import com.jasamedika.techtest.model.JenisKelaminComboBoxResponse;
import com.jasamedika.techtest.repository.JenisKelaminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JenisKelaminService {

    @Autowired
    private JenisKelaminRepository jenisKelaminRepository;

    public List<JenisKelaminComboBoxResponse> getComboBox(){
        List<JenisKelamin> jenisKelaminList = jenisKelaminRepository.findAll();
        return jenisKelaminList.stream().map(jenisKelamin -> toJenisKelaminComboBoxResponse(jenisKelamin)).toList();
    }

    private JenisKelaminComboBoxResponse toJenisKelaminComboBoxResponse(JenisKelamin jenisKelamin){
        return JenisKelaminComboBoxResponse
                .builder()
                .kdJenisKelamin(jenisKelamin.getKdJenisKelamin())
                .namaJenisKelamin(jenisKelamin.getNamaJenisKelamin())
                .build();
    }
}
