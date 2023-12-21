package com.gestionstock.Gestion.de.stock.services.impl;

import com.gestionstock.Gestion.de.stock.dto.UtilisateurDto;
import com.gestionstock.Gestion.de.stock.exception.EntityNotFoundException;
import com.gestionstock.Gestion.de.stock.exception.ErrorCodes;
import com.gestionstock.Gestion.de.stock.exception.InvalidEntityException;
import com.gestionstock.Gestion.de.stock.model.Utilisateur;
import com.gestionstock.Gestion.de.stock.repository.UtilisateurRepository;
import com.gestionstock.Gestion.de.stock.services.UtilisateurService;
import com.gestionstock.Gestion.de.stock.validator.UtilisateurValidator;
import jdk.jshell.execution.Util;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class UtilisateurServiceImpl implements UtilisateurService {

    private UtilisateurRepository utilisateurRepository;

    @Autowired
    public UtilisateurServiceImpl(
            UtilisateurRepository utilisateurRepository
    ){
        this.utilisateurRepository = utilisateurRepository;
    }

    @Override
    public UtilisateurDto save(UtilisateurDto utilisateurDto) {
        List<String> errors = UtilisateurValidator.validate(utilisateurDto);
        if (!errors.isEmpty()){
            log.error("Utilisateur non valide {}", utilisateurDto);
            throw new InvalidEntityException("le client n'est pas valide", ErrorCodes.UTILISATEUR_NOT_VALID, errors);
        }
        return UtilisateurDto.fromEntity((utilisateurRepository.save(utilisateurDto.toEntity(utilisateurDto))));
    }

    @Override
    public UtilisateurDto findById(Integer id) {
        if (id == null) {
            log.error("L'ID de l'Utilisateur est null");
            return null;
        }
        Optional<Utilisateur> utilisateur = utilisateurRepository.findById(id);

        UtilisateurDto utilisateurDto = UtilisateurDto.fromEntity(utilisateur.get());

        return Optional.of(utilisateurDto).orElseThrow(() -> new EntityNotFoundException(
                "Aucun utilisateur a été trouvé avec l'ID = " + id + "dans la base de donnée",
                ErrorCodes.UTILISATEUR_NOT_FOUND)
        );
    }


    @Override
    public List<UtilisateurDto> findAll() {
        return utilisateurRepository.findAll().stream()
                .map(UtilisateurDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Integer id) {
        if (id == null){
            log.error("L'ID de l'utilisateur est null");
            return;
        }
        utilisateurRepository.deleteById(id);
    }
}
