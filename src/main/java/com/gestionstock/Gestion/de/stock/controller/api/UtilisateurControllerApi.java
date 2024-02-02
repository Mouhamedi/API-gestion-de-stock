package com.gestionstock.Gestion.de.stock.controller.api;

import com.gestionstock.Gestion.de.stock.dto.UtilisateurDto;
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

@Api(APP_ROOT + "/utilisateur")
public interface UtilisateurControllerApi {

    @PostMapping(value = APP_ROOT + "/utilisateur/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Enregistrer un utilisateur", notes = "cette methodes permet d'enregistrer ou de modifier un utilisateur", response = UtilisateurDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "l'objet utilisateur cree / modifie"),
            @ApiResponse(code = 404, message = "l'objet utilisateur n'est pas valide")
    })
    UtilisateurDto save(UtilisateurDto utilisateurDto);

    @GetMapping(value = APP_ROOT + "/utilisateur/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Rechercher un utilisateur par ID", notes = "cette methodes permet de rechercher un utilisateur par son ID", response = UtilisateurDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "utilisateur a été trouvé dans la BDD"),
            @ApiResponse(code = 404, message = "Aucune utilisateur n'existe dans la BDD avec l'ID fourni")
    })
    UtilisateurDto findById(Integer id);


    @GetMapping(value = APP_ROOT + "/utilisateur/all", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Renvoie la liste des utilisateurs", notes = "cette methodes permet de rechercher et renvoyer la liste des utilisateurs qui existeent " +
            "dans la BDD", responseContainer = "List<UtilisateurDto>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La liste des utilisateurs / Une liste vide")
    })
    List<UtilisateurDto> findAll();

    @DeleteMapping(value = APP_ROOT + "/utilisateur/delete/{id}")
    @ApiOperation(value = "Supprimer un utilisateur", notes = "cette methodes permet de supprimer un utilisateur par son ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "utilisateur a été supprimé")
    })
    void delete(Integer id);
}
