package com.gestionstock.Gestion.de.stock.controller.api;

import com.gestionstock.Gestion.de.stock.dto.CategoryDto;
import com.gestionstock.Gestion.de.stock.dto.ClientDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import static com.gestionstock.Gestion.de.stock.utils.Constants.APP_ROOT;

@Api(APP_ROOT + "/clients")
public interface ClientControllerApi {

    @PostMapping(value = APP_ROOT + "/client/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Enregistrer un client", notes = "cette methodes permet d'enregistrer ou de modifier un client", response = ClientDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "l'objet client cree / modifie"),
            @ApiResponse(code = 404, message = "l'objet client n'est pas valide")
    })
    ClientDto save(@RequestBody ClientDto clientDto);

    @GetMapping(value = APP_ROOT + "/client/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Rechercher un client par ID", notes = "cette methodes permet de rechercher un client par son ID", response = ClientDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "client été trouvé dans la BDD"),
            @ApiResponse(code = 404, message = "Aucune client n'existe dans la BDD avec l'ID fourni")
    })
    ClientDto findById(@PathVariable("id") Integer id);


    @GetMapping(value = APP_ROOT + "/client/all", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Renvoie la liste des clients", notes = "cette methodes permet de rechercher et renvoyer la liste des clients qui existeent " +
            "dans la BDD", responseContainer = "List<ClientDto>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La liste des categories / Une liste vide")
    })
    List<ClientDto> findAll();

    @DeleteMapping(value = APP_ROOT + "/client/delete/{id}")
    @ApiOperation(value = "Supprimer un client", notes = "cette methodes permet de supprimer un client par son ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "client a été supprimé")
    })
    void delete(@PathVariable("id") Integer id);
}

