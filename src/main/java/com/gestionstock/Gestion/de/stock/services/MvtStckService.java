package com.gestionstock.Gestion.de.stock.services;


import com.gestionstock.Gestion.de.stock.dto.MvtStckDto;

import java.util.List;

public interface MvtStckService {

    MvtStckDto save(MvtStckDto mvtStckDto);

    MvtStckDto findById(Integer id);

    List<MvtStckDto> findAll();

    void delete(Integer id);
}
