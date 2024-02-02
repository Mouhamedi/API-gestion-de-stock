package com.gestionstock.Gestion.de.stock.services.impl;

import com.gestionstock.Gestion.de.stock.dto.LigneVentesDto;
import com.gestionstock.Gestion.de.stock.dto.VentesDto;
import com.gestionstock.Gestion.de.stock.exception.EntityNotFoundException;
import com.gestionstock.Gestion.de.stock.exception.ErrorCodes;
import com.gestionstock.Gestion.de.stock.exception.InvalidEntityException;
import com.gestionstock.Gestion.de.stock.model.Article;
import com.gestionstock.Gestion.de.stock.model.LigneVentes;
import com.gestionstock.Gestion.de.stock.model.Ventes;
import com.gestionstock.Gestion.de.stock.repository.ArticleRepository;
import com.gestionstock.Gestion.de.stock.repository.LigneVentesRepository;
import com.gestionstock.Gestion.de.stock.repository.VentesRepository;
import com.gestionstock.Gestion.de.stock.services.VentesService;
import com.gestionstock.Gestion.de.stock.validator.VentesValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class VentesServiceImpl implements VentesService {

    private ArticleRepository articleRepository;
    private VentesRepository ventesRepository;
    private LigneVentesRepository ligneVentesRepository;

    @Autowired
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
            throw new InvalidEntityException("Ventes non valide ", ErrorCodes.VENTES_NOT_VALID, errors);
        }

        List<String> articleErrors = new ArrayList<>();

        ventesDto.getLigneVentesDto().forEach(ligneVentesDto -> {
           Optional<Article> article = articleRepository.findById(ligneVentesDto.getArticle().getId());
           if (article.isEmpty()){
               articleErrors.add("Aucun article avec l'ID" + ligneVentesDto.getArticle().getId() + "n'a été trouvé dans la base de donnée");
           }
        });

        if (!articleErrors.isEmpty()){
            log.error("Article non trouvé dans la Base de donnée");
            throw new InvalidEntityException("Les articles n'ont pas été trouvé dans la base de donnée", ErrorCodes.VENTES_NOT_VALID,errors);

        }

        Ventes savedVentes = ventesRepository.save(VentesDto.toEntity(ventesDto));

        ventesDto.getLigneVentesDto().forEach( ligneVentesDto -> {
            LigneVentes ligneVentes = LigneVentesDto.toEntity(ligneVentesDto);
            ligneVentesRepository.save(ligneVentes);
        });

        return VentesDto.fromEntity(savedVentes);
    }

    @Override
    public VentesDto findById(Integer id) {

        if (id == null){
            log.error("L'ID de la Vente est null");
            return null;
        }
        return ventesRepository.findById(id)
                .map(VentesDto::fromEntity)
                .orElseThrow(() -> new EntityNotFoundException("Aucune vente n'a été trouve dans la base de donnée", ErrorCodes.VENTES_NOT_FOUND));
    }

    @Override
    public VentesDto findByCode(String code) {
        if (!StringUtils.hasLength(code)){
            log.error("Le CODE dela vente est null");
            return null;
        }
        return ventesRepository.findVentesByCode(code)
                .map(VentesDto::fromEntity)
                .orElseThrow(() -> new EntityNotFoundException("Aucune vente n'a été trouve avec le CODE"+ code + "dans la base de donnée", ErrorCodes.VENTES_NOT_FOUND));

    }

    @Override
    public List<VentesDto> findAll() {

        return ventesRepository.findAll().stream()
                .map(VentesDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Integer id) {
        if (id == null){
            log.error("L'ID de la vente est NULL");
            return;
        }
        ventesRepository.deleteById(id);

    }
}
