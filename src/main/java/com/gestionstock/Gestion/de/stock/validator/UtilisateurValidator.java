package com.gestionstock.Gestion.de.stock.validator;

import com.gestionstock.Gestion.de.stock.dto.UtilisateurDto;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class UtilisateurValidator {
    public static List<String> validate(UtilisateurDto utilisateurDto){
        List<String> errors = new ArrayList<>();

        if (utilisateurDto == null) {
            errors.add("Veuillez entrer le nom d'utilisateur");
            errors.add("Veuillez entrer le prenom d'utilisateur");
            errors.add("Veuillez entrer la date de naissance d'utilisateur");
            errors.add("Veuillez entrer le mot de passe d'utilisateur");
            errors.add("Veuillez entrer le mail d'utilisateur");
            errors.add("Veuillez entrer la ville d'utilisateur");
            errors.add("Veuillez entrer l'adresse d'utilisateur");
            errors.add("Veuillez entrer le pays d'utilisateur");
            errors.add("Veuillez entrer le code postal d'utilisateur");
            return errors;

        }
        if (!StringUtils.hasLength(utilisateurDto.getNom())) {
            errors.add("Veuillez entrer le nom d'utilisateur");
        }
        if (!StringUtils.hasLength(utilisateurDto.getPrenom())) {
            errors.add("Veuillez entrer le prenom d'utilisateur");
        }
        if (utilisateurDto.getDateDeNaissance() == null){
            errors.add("Veuillez entrer la date de naissance d'utilisateur");
        }
        if (!StringUtils.hasLength(utilisateurDto.getMotDePasse())){
            errors.add("Veuillez entrer le mot de passe d'utilisateur");
        }
        if (!StringUtils.hasLength(utilisateurDto.getAdresse())){
            errors.add("Veuillez entrer l'adresse d'utilisateur");
        }
        if (!StringUtils.hasLength(utilisateurDto.getVille())){
            errors.add("Veuillez entrer la ville d'utilisateur");
        }
        if (!StringUtils.hasLength(utilisateurDto.getPays())){
            errors.add("Veuillez entrer le pays d'utilisateur");
        }
        if (!StringUtils.hasLength(utilisateurDto.getCodePostale())){
            errors.add("Veuillez entrer le code postal d'utilisateur");
        }
        if (!StringUtils.hasLength(utilisateurDto.getMail())){
            errors.add("Veuillez entrer le mail d'utilisateur");
        }
        return errors;
    }
}
