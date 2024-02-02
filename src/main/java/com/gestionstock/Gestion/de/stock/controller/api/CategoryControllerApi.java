package com.gestionstock.Gestion.de.stock.controller.api;

import com.gestionstock.Gestion.de.stock.dto.ArticleDto;
import com.gestionstock.Gestion.de.stock.dto.CategoryDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import static com.gestionstock.Gestion.de.stock.utils.Constants.APP_ROOT;

@Api(APP_ROOT + "/categorie")
public interface CategoryControllerApi {

    @PostMapping(value = APP_ROOT + "/category/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Enregistrer un categorie", notes = "cette methodes permet d'enregistrer ou de modifier une categorie", response = CategoryDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "l'objet categorie cree / modifie"),
            @ApiResponse(code = 404, message = "l'objet categorie n'est pas valide")
    })
    CategoryDto save(@RequestBody CategoryDto categoryDto);


    @GetMapping(value = APP_ROOT + "/category/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Rechercher un categorie par ID", notes = "cette methodes permet de rechercher une categorie par son ID", response = CategoryDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "categorie a été trouvé dans la BDD"),
            @ApiResponse(code = 404, message = "Aucune categorie n'existe dans la BDD avec l'ID fourni")
    })
    CategoryDto findById(@PathVariable("id") Integer id);

    @GetMapping(value = APP_ROOT + "/category/{code}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Rechercher une categorie par CODE", notes = "cette methodes permet de rechercher une categorie par son CODE", response = CategoryDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "categorie a été trouvé dans la BDD"),
            @ApiResponse(code = 404, message = "Aucune categorie n'existe dans la BDD avec le CODE fourni")
    })
    CategoryDto findByCode(@PathVariable("code") String code);

    @GetMapping(value = APP_ROOT + "/category/all", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Renvoie la liste des categories", notes = "cette methodes permet de rechercher et renvoyer la liste des categories qui existeent " +
            "dans la BDD", responseContainer = "List<CategoryDto>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La liste des categories / Une liste vide")
    })
    List<CategoryDto> findAll();

    @DeleteMapping(value = APP_ROOT + "/category/delete/{id}")
    @ApiOperation(value = "Supprimer une categorie", notes = "cette methodes permet de supprimer une categorie par son ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "categorie a été supprimé")
    })
    void  delete(@PathVariable("id") Integer id);
}