package com.gestionstock.Gestion.de.stock.validator;

import com.gestionstock.Gestion.de.stock.dto.EntrepriseDto;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class EntrepriseValidator {
    public static List<String> validate(EntrepriseDto entrepriseDto){
        List<String> errors = new ArrayList<>();

        if (entrepriseDto == null) {
            errors.add("Veuillez entrer le nom de l'entreprise");
            errors.add("Veuillez entrer le secteur d'activite de l'entreprise");
            errors.add("Veuillez entrer la description de l'entreprise");
            errors.add("Veuillez entrer le mail de l'entreprise");
            errors.add("Veuillez entrer la description de l'entreprise");
            return errors;

        }
        if (!StringUtils.hasLength(entrepriseDto.getNom())) {
            errors.add("Veuillez entrer le nom de l'entreprise");
        }
        if (!StringUtils.hasLength(entrepriseDto.getSecteuractivite())){
            errors.add("Veuillez entrer le secteur d'activite de l'entreprise");
        }
        if (!StringUtils.hasLength(entrepriseDto.getDescription())){
            errors.add("Veuillez entrer la description de l'entreprise");
        }
        if (!StringUtils.hasLength(entrepriseDto.getTelephone())){
            errors.add("Veuillez entrer le mot de passe de l'entreprise");
        }
        if (!StringUtils.hasLength(entrepriseDto.getMail())){
            errors.add("Veuillez entrer le mail de l'entreprise");
        }
        return errors;
    }
}
