package com.gestionstock.Gestion.de.stock.services.impl;

import com.gestionstock.Gestion.de.stock.dto.VentesDto;
import com.gestionstock.Gestion.de.stock.exception.ErrorCodes;
import com.gestionstock.Gestion.de.stock.exception.InvalidEntityException;
import com.gestionstock.Gestion.de.stock.model.Article;
import com.gestionstock.Gestion.de.stock.repository.ArticleRepository;
import com.gestionstock.Gestion.de.stock.repository.LigneVentesRepository;
import com.gestionstock.Gestion.de.stock.repository.VentesRepository;
import com.gestionstock.Gestion.de.stock.services.VentesService;
import com.gestionstock.Gestion.de.stock.validator.VentesValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class VentesServiceImpl implements VentesService {

    private ArticleRepository articleRepository;
    private VentesRepository ventesRepository;
    private LigneVentesRepository ligneVentesRepository;

    public VentesServiceImpl(
            ArticleRepository articleRepository,
            VentesRepository ventesRepository,
            LigneVentesRepository ligneVentesRepository
    ) {
        this.articleRepository = articleRepository;
        this.ventesRepository = ventesRepository;
        this.ligneVentesRepository = ligneVentesRepository;
    }


    @Override
    public VentesDto save(VentesDto ventesDto) {
        List<String> errors = VentesValidator.validate(ventesDto);
        if (!errors.isEmpty()){
            log.error("La Ventes n'est pas valide");
            throw new InvalidEntityException("", ErrorCodes.VENTES_NOT_VALID, errors);
        }

        ventesDto.getLigneventes().forEach(ligneVentesDto -> {
           
        });
        return null;
    }

    @Override
    public VentesDto findById(Integer id) {

        return null;
    }

    @Override
    public VentesDto findByCodeArticle(String codeArticle) {

        return null;
    }

    @Override
    public List<VentesDto> findAll() {

        return null;
    }

    @Override
    public void delete(Integer id) {

    }
}
