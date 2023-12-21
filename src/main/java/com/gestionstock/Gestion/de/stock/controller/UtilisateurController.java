package com.gestionstock.Gestion.de.stock.controller;

import com.gestionstock.Gestion.de.stock.controller.api.UtilisateurControllerApi;
import com.gestionstock.Gestion.de.stock.dto.UtilisateurDto;
import com.gestionstock.Gestion.de.stock.services.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UtilisateurController implements UtilisateurControllerApi {

    private  UtilisateurService utilisateurService;

    @Autowired
    public UtilisateurController(
            UtilisateurService utilisateurService
    ) {
        this.utilisateurService = utilisateurService;
    }

    @Override
    public UtilisateurDto save(UtilisateurDto utilisateurDto) {

        return utilisateurService.save(utilisateurDto);
    }

    @Override
    public UtilisateurDto findById(Integer id) {

        return utilisateurService.findById(id);
    }


    @Override
    public List<UtilisateurDto> findAll() {

        return utilisateurService.findAll();
    }

    @Override
    public void delete(Integer id) {
        utilisateurService.delete(id);

    }
}
