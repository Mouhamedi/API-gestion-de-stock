package com.gestionstock.Gestion.de.stock.controller;

import com.gestionstock.Gestion.de.stock.controller.api.VentesControllerApi;
import com.gestionstock.Gestion.de.stock.dto.VentesDto;
import com.gestionstock.Gestion.de.stock.services.VentesService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class VentesController implements VentesControllerApi {

    private VentesService ventesService;

    @Autowired
    public VentesController(VentesService ventesService) {
        this.ventesService = ventesService;
    }

    @Override
    public VentesDto save(VentesDto ventesDto) {
        return ventesService.save(ventesDto);
    }

    @Override
    public VentesDto findById(Integer id) {
        return ventesService.findById(id);
    }

    @Override
    public VentesDto findByCode(String code) {
        return ventesService.findByCode(code);
    }

    @Override
    public List<VentesDto> findAll() {
        return ventesService.findAll();
    }

    @Override
    public void delete(Integer id) {
        ventesService.delete(id);

    }
}
