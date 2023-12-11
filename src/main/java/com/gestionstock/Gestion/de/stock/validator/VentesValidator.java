package com.gestionstock.Gestion.de.stock.validator;


import com.gestionstock.Gestion.de.stock.dto.VentesDto;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class VentesValidator {
    public static List<String> validate(VentesDto ventesDto) {
        List<String> errors = new ArrayList<>();

        if (ventesDto == null){
            errors.add("Veuillez entrer le code de vente");
            errors.add("Veuillez entrer la date de vente");
            return errors;
        }

        if (!StringUtils.hasLength(ventesDto.getCode())) {
            errors.add("Veuillez entrer le code de vente");
        }
        if (ventesDto.getDateVente() == null) {
            errors.add("Veuillez entrer la date de vente");
        }
        return errors;
    }
}
