package com.gestionstock.Gestion.de.stock.services;

import com.gestionstock.Gestion.de.stock.dto.VentesDto;

import java.util.List;

public interface VentesService {

    VentesDto save(VentesDto ventesDto);

    VentesDto findById(Integer id);

    VentesDto findByCode(String code);

    List<VentesDto> findAll();

    void delete(Integer id);
}
