package com.jasamedika.techtest.service;

import com.jasamedika.techtest.entity.UnitKerja;
import com.jasamedika.techtest.model.UnitKerjaComboBoxResponse;
import com.jasamedika.techtest.repository.UnitKerjaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UnitKerjaService {

    @Autowired
    private UnitKerjaRepository unitKerjaRepository;

    public List<UnitKerjaComboBoxResponse> getComboBox(){
        List<UnitKerja> unitKerjaList = unitKerjaRepository.findAll();
        return unitKerjaList.stream().map(unitKerja -> toUnitKerjaComboBoxResponse(unitKerja)).toList();
    }

    private UnitKerjaComboBoxResponse toUnitKerjaComboBoxResponse(UnitKerja unitKerja){
        return UnitKerjaComboBoxResponse
                .builder()
                .kdUnitKerja(unitKerja.getKdUnitKerja())
                .namaUnitKerja(unitKerja.getNamaUnitKerja())
                .build();
    }
}
