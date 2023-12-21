package com.gestionstock.Gestion.de.stock.controller;

import com.gestionstock.Gestion.de.stock.controller.api.EntrepriseControllerApi;
import com.gestionstock.Gestion.de.stock.dto.EntrepriseDto;
import com.gestionstock.Gestion.de.stock.services.EntrepriseService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class EntrepriseController implements EntrepriseControllerApi {

    private EntrepriseService entrepriseService;

    @Autowired
    public EntrepriseController(
            EntrepriseService entrepriseService
    ){
        this.entrepriseService = entrepriseService;
    }
    @Override
    public EntrepriseDto save(EntrepriseDto entrepriseDto) {

        return entrepriseService.save(entrepriseDto);
    }

    @Override
    public EntrepriseDto findById(Integer id) {

        return entrepriseService.findById(id);
    }


    @Override
    public List<EntrepriseDto> findAll() {

        return entrepriseService.findAll();
    }

    @Override
    public void delete(Integer id) {

        entrepriseService.delete(id);
    }
}
