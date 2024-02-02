package com.gestionstock.Gestion.de.stock.controller.api;

import com.gestionstock.Gestion.de.stock.dto.EntrepriseDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.gestionstock.Gestion.de.stock.utils.Constants.APP_ROOT;

@Api(APP_ROOT + "/entreprise")
public interface EntrepriseControllerApi {


    @PostMapping(value = APP_ROOT + "/entreprise/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Enregistrer une entreprise", notes = "cette methodes permet d'enregistrer ou de modifier une entreprise", response = EntrepriseDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "l'objet entreprise cree / modifie"),
            @ApiResponse(code = 404, message = "l'objet entreprise n'est pas valide")
    })
    EntrepriseDto save(@RequestBody EntrepriseDto entrepriseDto);

    @GetMapping(value = APP_ROOT + "/entreprise/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Rechercher une entreprise par ID", notes = "cette methodes permet de rechercher une entreprise par son ID", response = EntrepriseDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "entreprise a été trouvé dans la BDD"),
            @ApiResponse(code = 404, message = "Aucune entreprise n'existe dans la BDD avec l'ID fourni")
    })
    EntrepriseDto findById(@PathVariable("id") Integer id);


    @GetMapping(value = APP_ROOT + "/entreprise/all", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Renvoie la liste des entreprises", notes = "cette methodes permet de rechercher et renvoyer la liste des entreprises qui existeent " +
            "dans la BDD", responseContainer = "List<EntrepriseDto>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La liste des entreprises / Une liste vide")
    })
    List<EntrepriseDto> findAll();

    @DeleteMapping(value = APP_ROOT + "/entreprise/delete/{id}")
    @ApiOperation(value = "Supprimer une entreprise", notes = "cette methodes permet de supprimer une entreprise par son ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "entreprise a été supprimé")
    })
    void delete(@PathVariable Integer id);
}
