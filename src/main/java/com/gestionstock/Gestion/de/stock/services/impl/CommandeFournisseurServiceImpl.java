package com.gestionstock.Gestion.de.stock.services.impl;


import com.gestionstock.Gestion.de.stock.dto.CommandeFournisseurDto;
import com.gestionstock.Gestion.de.stock.dto.LigneCommandeFournisseurDto;
import com.gestionstock.Gestion.de.stock.exception.EntityNotFoundException;
import com.gestionstock.Gestion.de.stock.exception.ErrorCodes;
import com.gestionstock.Gestion.de.stock.exception.InvalidEntityException;
import com.gestionstock.Gestion.de.stock.model.*;
import com.gestionstock.Gestion.de.stock.repository.*;
import com.gestionstock.Gestion.de.stock.services.CommandeFournisseurService;
import com.gestionstock.Gestion.de.stock.validator.CommandeFournisseurValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@Slf4j
public class CommandeFournisseurServiceImpl implements CommandeFournisseurService {


    private CommandeFournisseurRepository commandeFournisseurRepository;
    private LigneCommandeFournisseurRepository ligneCommandeFournisseurRepository;
    private FournisseurRepository fournisseurRepository;
    private ArticleRepository articleRepository;

    public CommandeFournisseurServiceImpl(
            CommandeFournisseurRepository commandeFournisseurRepository,
            LigneCommandeFournisseurRepository ligneCommandeFournisseurRepository,
            FournisseurRepository fournisseurRepository,
            ArticleRepository articleRepository
    ) {
        this.commandeFournisseurRepository = commandeFournisseurRepository;
        this.ligneCommandeFournisseurRepository = ligneCommandeFournisseurRepository;
        this.fournisseurRepository = fournisseurRepository;
        this.articleRepository = articleRepository;
    }

    @Override
    public CommandeFournisseurDto save(CommandeFournisseurDto commandeFournisseurDto) {

        List<String> errors = CommandeFournisseurValidator.validate(commandeFournisseurDto);

        if (!errors.isEmpty()){
            log.error("La commande du fournisseur n'est pas valide");
            throw new InvalidEntityException("la commande du fournisseur 'est pas valide", ErrorCodes.COMMANDE_FOURNISSEUR_NOT_VALID, errors);
        }
        Optional<Fournisseur> fournisseur = fournisseurRepository.findById(commandeFournisseurDto.getFournisseur().getId());
        if (!fournisseur.isEmpty()){
            log.warn("L'ID {} du fournisseur n'est pas trouvé dans la base de donnée", commandeFournisseurDto.getFournisseur().getId());
            throw  new EntityNotFoundException(
                    "Aucun fournisseur n'a été trouvé avec l'ID = " + commandeFournisseurDto.getFournisseur().getId() + "dans la base de donnée", ErrorCodes.FOURNISSEUR_NOT_FOUND);


        }
        List<String> articleErrors = new ArrayList<>();

        if (commandeFournisseurDto.getLigneCommandeFournisseurs() != null) {
            commandeFournisseurDto.getLigneCommandeFournisseurs().forEach(ligCmdFrs -> {
                if (ligCmdFrs.getArticle().getId() != null) {
                    Optional<Article> article = articleRepository.findById(ligCmdFrs.getArticle().getId());
                    if (article.isEmpty()) {
                        articleErrors.add("L'article avec l'ID" + ligCmdFrs.getArticle().getId() + "n'existe pas");
                    } else {
                        articleErrors.add("Impossible d'enregistrer une commande avec un  article NULL");
                    }
                }
            });
        }
        if (!articleErrors.isEmpty()){
            log.warn("");
            throw new InvalidEntityException("L'article n'existe pas danss la base de donnée", ErrorCodes.ARTICLE_NOT_FOUND, articleErrors);
        }

        CommandeFournisseur savedCmdFrs = commandeFournisseurRepository.save(CommandeFournisseurDto.toEntity(commandeFournisseurDto));
        if (commandeFournisseurDto.getLigneCommandeFournisseurs() != null){
            commandeFournisseurDto.getLigneCommandeFournisseurs().forEach(ligCmdFrs-> {
                LigneCommandeFournisseur ligneCommandeFournisseur = LigneCommandeFournisseurDto.toEntity(ligCmdFrs);
                ligneCommandeFournisseur.setCommandeFournisseur(savedCmdFrs);
                ligneCommandeFournisseurRepository.save(ligneCommandeFournisseur);

            });
        }

        return CommandeFournisseurDto.fromEntity(savedCmdFrs);
    }

    @Override
    public CommandeFournisseurDto findById(Integer id) {
        if (id == null){
            log.error("l'ID de la Commande du fournisseur est null");
            return null;
        }
        return commandeFournisseurRepository.findById(id)
                .map(CommandeFournisseurDto::fromEntity)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Aucune commande du fournisseur n'a été trouvé avec l'ID " + id  , ErrorCodes.COMMANDE_FOURNISSEUR_NOT_FOUND
                ));
    }

    @Override
    public CommandeFournisseurDto findByCode(String code) {
            if (!StringUtils.hasLength(code)){
                log.error("le CODE de la Commande du fournisseur est null");
                return null;
            }
            return commandeFournisseurRepository.findCommadeFournisseurByCode(code)
                    .map(CommandeFournisseurDto::fromEntity)
                    .orElseThrow(() -> new EntityNotFoundException(
                            "Aucune commande du fournisseur n'a été trouvé avec le CODE " + code , ErrorCodes.COMMANDE_FOURNISSEUR_NOT_FOUND
                    ));
    }

    @Override
    public List<CommandeFournisseurDto> findAll() {
        return commandeFournisseurRepository.findAll().stream()
                .map(CommandeFournisseurDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Integer id) {

        if (id == null){
            log.error("l'ID de la Commande du fournisseur est null");
            return;
        }
        commandeFournisseurRepository.deleteById(id);
    }
}
