package com.gestionstock.Gestion.de.stock.controller.api;

import com.gestionstock.Gestion.de.stock.dto.VentesDto;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.gestionstock.Gestion.de.stock.utils.Constants.APP_ROOT;

@Api(APP_ROOT + "/ventes")
public interface VentesControllerApi {

    @PostMapping(APP_ROOT + "/ventes/create")
    VentesDto save(@RequestBody VentesDto ventesDto);

    @GetMapping(APP_ROOT + "/ventes/{idVente}")
    VentesDto findById(@PathVariable("idVente") Integer id);

    @GetMapping(APP_ROOT + "/ventes/{codeVente}")
    VentesDto findByCode(@PathVariable("codeVente") String code);

    @GetMapping(APP_ROOT + "/ventes/all")
    List<VentesDto> findAll();

    @DeleteMapping(APP_ROOT + "/ventes/delete/{idVente}")
    void delete(@PathVariable("idVente") Integer id);
}
