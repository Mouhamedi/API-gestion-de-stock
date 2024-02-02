package com.gestionstock.Gestion.de.stock.validator;



import com.gestionstock.Gestion.de.stock.dto.CommandeClientDto;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class CommandeClientValidator {

    public static List<String> validate(CommandeClientDto commandeClientDto) {
        List<String> errors = new ArrayList<>();

        if (commandeClientDto == null){
            errors.add("Veuillez entrer le code de la commande");
            errors.add("Veuillez entrer la date de la commande");
            return errors;
        }

        if (!StringUtils.hasLength(commandeClientDto.getCode())) {
            errors.add("Veuillez entrer le code de la commande");
        }
        if (commandeClientDto.getDateCommande() == null) {
            errors.add("Veuillez entrer la date de la commande");
        }
        return errors;
    }
}
