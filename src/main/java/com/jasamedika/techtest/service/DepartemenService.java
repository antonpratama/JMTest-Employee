package com.jasamedika.techtest.service;

import com.jasamedika.techtest.entity.Departemen;
import com.jasamedika.techtest.model.DepartemenComboBoxResponse;
import com.jasamedika.techtest.repository.DepartemenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartemenService {

    @Autowired
    private DepartemenRepository departemenRepository;

    public List<DepartemenComboBoxResponse> getComboBox(){
        List<Departemen> listDepartemen = departemenRepository.findAll();
        return listDepartemen.stream().map(departemen -> toDepartemenComboBoxResponse(departemen)).toList();
    }

    private DepartemenComboBoxResponse toDepartemenComboBoxResponse(Departemen departemen){
        return DepartemenComboBoxResponse.builder()
                .kdDepartemen(departemen.getKdDepartemen())
                .namaDepartemen(departemen.getNamaDepartemen())
                .build();
    }
}
