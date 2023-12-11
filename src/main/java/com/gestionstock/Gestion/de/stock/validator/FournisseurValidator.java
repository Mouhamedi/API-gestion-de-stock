package com.gestionstock.Gestion.de.stock.validator;

import com.gestionstock.Gestion.de.stock.dto.FournisseurDto;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class FournisseurValidator {
    public static List<String> validate(FournisseurDto fournisseurDto) {
        List<String> errors = new ArrayList<>();

        if (fournisseurDto == null){
            errors.add("Veuillez entrer le nom du fournisseur");
            errors.add("Veuillez entrer le prenom du fournisseur");
            errors.add("Veuillez entrer le mail du fournisseur");
            errors.add("Veuillez entrer le numero dr telephone du fournisseur");
            return errors;
        }

        if (!StringUtils.hasLength(fournisseurDto.getNom())) {
            errors.add("Veuillez entrer le nom du fournisseur");
        }
        if (!StringUtils.hasLength(fournisseurDto.getPrenom())) {
            errors.add("Veuillez entrer le prenom du fournisseur");
        }
        if (!StringUtils.hasLength(fournisseurDto.getMail())) {
            errors.add("Veuillez entrer le mail du fournisseur");
        }
        if (!StringUtils.hasLength(fournisseurDto.getTelephone())) {
            errors.add("Veuillez entrer le numero de telephone du fournisseur");
        }
        return errors;
    }
}
