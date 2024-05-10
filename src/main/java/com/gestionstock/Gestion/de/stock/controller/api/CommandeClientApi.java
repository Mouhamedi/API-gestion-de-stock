package com.gestionstock.Gestion.de.stock.controller.api;

import com.gestionstock.Gestion.de.stock.dto.CommandeClientDto;
import com.gestionstock.Gestion.de.stock.model.EtatCommande;
import io.swagger.annotations.Api;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.gestionstock.Gestion.de.stock.utils.Constants.APP_ROOT;

@Api(APP_ROOT + "/commandesclient")
public interface CommandeClientApi {

    @PostMapping(APP_ROOT + "/commandesclient/create")
    ResponseEntity<CommandeClientDto> save(@RequestBody CommandeClientDto commandeClientDto);

    @PatchMapping(APP_ROOT + "/commandesclient/update/{idCommande}/{etatCommande}")
    ResponseEntity<CommandeClientDto> updateEtatCommande(@PathVariable("idCommade") Integer idCommande, @PathVariable("etatCommande") EtatCommande etatCommande);

    @GetMapping(APP_ROOT + "/commandesclient/{idCommandeClient}")
    ResponseEntity<CommandeClientDto> findById(@PathVariable("idCommandeClient")  Integer id);

    @GetMapping(APP_ROOT + "/commandesclient/{codeCommandeClient}")
    ResponseEntity<CommandeClientDto> findByCode(@PathVariable("codeCommandeClient") String code);

    @GetMapping(APP_ROOT + "/commandesclient/all")
    ResponseEntity<List<CommandeClientDto>> findAll();

    @DeleteMapping(APP_ROOT + "/commandesclient/delete/{idCommandeClient}")
    ResponseEntity  delete(@PathVariable("idCommandeClient") Integer id);
}
