package com.gestionstock.Gestion.de.stock.validator;

import com.gestionstock.Gestion.de.stock.dto.MvtStckDto;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class MvtStckValidator {
    public static List<String> validate(MvtStckDto mvtStckDto){
        List<String> errors = new ArrayList<>();

        if (mvtStckDto == null) {
            errors.add("Veuillez entrer la quantité du mouvement stock");
            errors.add("Veuillez entrer la date du mouvement stock");
            return errors;
        }

        if (mvtStckDto.getQuantite() == null) {
            errors.add("Veuillez entrer la quantité du mouvement stock");
        }

        if (mvtStckDto.getDateMvt() == null){
            errors.add("Veuillez entrer la date du mouvement stock");
        }

            return errors;
    }
}
