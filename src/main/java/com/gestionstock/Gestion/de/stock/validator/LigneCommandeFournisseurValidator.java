package com.gestionstock.Gestion.de.stock.validator;

import com.gestionstock.Gestion.de.stock.dto.LigneCommandeFournisseurDto;

import java.util.ArrayList;
import java.util.List;

public class LigneCommandeFournisseurValidator {

    public static List<String> validate(LigneCommandeFournisseurDto ligneCommandeFournisseurDto) {
        List<String> errors = new ArrayList<>();

        if (ligneCommandeFournisseurDto == null) {
            errors.add("Veuillez entrer la quantité de la commande");
            errors.add("Veuillez entrer le prix unitaire de la commande");
            return errors;
        }

        if (ligneCommandeFournisseurDto.getQuantite() == null) {
            errors.add("Veuillez entrer la quantité de la commande");
        }

        if (ligneCommandeFournisseurDto.getPrixUnitaire() == null){
            errors.add("Veuillez entrer le prix unitaire de la commande");
        }

        return errors;
    }
}
