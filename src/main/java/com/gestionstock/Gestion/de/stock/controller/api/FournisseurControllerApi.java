package com.gestionstock.Gestion.de.stock.controller.api;

import com.gestionstock.Gestion.de.stock.dto.FournisseurDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

import static com.gestionstock.Gestion.de.stock.utils.Constants.APP_ROOT;

@Api(APP_ROOT + "/fournisseurs")
public interface FournisseurControllerApi {

    @PostMapping(value = APP_ROOT + "/fournisseur/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Enregistrer un fournisseur", notes = "cette methodes permet d'enregistrer ou de modifier un fournisseur", response = FournisseurDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "l'objet fournisseur cree / modifie"),
            @ApiResponse(code = 404, message = "l'objet fournisseur n'est pas valide")
    })
    FournisseurDto save(FournisseurDto fournisseurDto);

    @GetMapping(value = APP_ROOT + "/fournisseur/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Rechercher un fournisseur par ID", notes = "cette methodes permet de rechercher un fournisseur par son ID", response = FournisseurDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "fournisseur a été trouvé dans la BDD"),
            @ApiResponse(code = 404, message = "Aucune fournisseur n'existe dans la BDD avec l'ID fourni")
    })
    FournisseurDto findById(Integer id);


    @GetMapping(value = APP_ROOT + "/fournisseur/all", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Renvoie la liste des fournisseurs", notes = "cette methodes permet de rechercher et renvoyer la liste des fournisseurs qui existeent " +
            "dans la BDD", responseContainer = "List<FournisseurDto>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La liste des fournisseurs / Une liste vide")
    })
    List<FournisseurDto> findAll();

    @DeleteMapping(value = APP_ROOT + "/fournisseur/delete/{id}")
    @ApiOperation(value = "Supprimer un fournisseur", notes = "cette methodes permet de supprimer un fournisseur par son ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "fournisseur a été supprimé")
    })
    void delete(Integer id);
}
