package com.gestionstock.Gestion.de.stock.validator;

import com.gestionstock.Gestion.de.stock.dto.LigneCommandeClientDto;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class LigneCommandeClientValidator {

    public static List<String> validate(LigneCommandeClientDto ligneCommandeClientDto) {
        List<String> errors = new ArrayList<>();

        if (ligneCommandeClientDto == null) {
            errors.add("Veuillez entrer la quantité de la commande");
            errors.add("Veuillez entrer le prix unitaire de la commande");
            return errors;
        }

        if (ligneCommandeClientDto.getQuantite() == null) {
            errors.add("Veuillez entrer la quantité de la commande");
        }

        if (ligneCommandeClientDto.getPrixUnitaire() == null){
            errors.add("Veuillez entrer le prix unitaire de la commande");
        }

        return errors;
    }
}
