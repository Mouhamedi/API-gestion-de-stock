package com.gestionstock.Gestion.de.stock.controller.api;


import com.gestionstock.Gestion.de.stock.dto.CommandeFournisseurDto;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;
import java.util.List;

import static com.gestionstock.Gestion.de.stock.utils.Constants.APP_ROOT;

@Api(APP_ROOT +"/commandesfournisseur")
public interface CommandeFournisseurApi {

    @PostMapping(APP_ROOT + "/commandesfournisseur/create")
    CommandeFournisseurDto save(@RequestBody CommandeFournisseurDto commandeFournisseurDto);

    @GetMapping(APP_ROOT + "/commandesfournisseur/{idCommandeFournisseur}")
    CommandeFournisseurDto findById(@PathVariable("idCommandeFournisseur") Integer id);

    @GetMapping(APP_ROOT + "/commandesfournisseur/{codeCommandeFournisseur}")
    CommandeFournisseurDto findByCode(@PathVariable("codeCommandeFournisseur") String code);

    @GetMapping(APP_ROOT + "/commandesfournisseur/all")
    List<CommandeFournisseurDto> findAll();

    @DeleteMapping(APP_ROOT + "/commandesfournisseur/delete/{idCommandeFournisseur}")
    void  delete(@PathVariable("idCommandeFournisseur") Integer id);

}
