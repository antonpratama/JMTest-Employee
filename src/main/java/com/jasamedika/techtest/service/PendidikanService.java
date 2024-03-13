package com.jasamedika.techtest.service;

import com.jasamedika.techtest.entity.Pendidikan;
import com.jasamedika.techtest.model.PendidikanComboBoxResponse;
import com.jasamedika.techtest.repository.PendidikanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PendidikanService {

    @Autowired
    private PendidikanRepository pendidikanRepository;

    public List<PendidikanComboBoxResponse> getComboBox(){
        List<Pendidikan> pendidikanList = pendidikanRepository.findAll();
        return pendidikanList.stream().map(pendidikan -> toUnitKerjaComboBoxResponse(pendidikan)).toList();
    }

    private PendidikanComboBoxResponse toUnitKerjaComboBoxResponse(Pendidikan pendidikan){
        return PendidikanComboBoxResponse
                .builder()
                .kdPendidikan(pendidikan.getKdPendidikan())
                .namaPendidikan(pendidikan.getNamaPendidikan())
                .build();
    }
}
