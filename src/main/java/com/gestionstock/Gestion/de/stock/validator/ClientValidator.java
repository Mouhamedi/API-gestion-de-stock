package com.gestionstock.Gestion.de.stock.validator;



import com.gestionstock.Gestion.de.stock.dto.ClientDto;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class ClientValidator {
    public static List<String> validate(ClientDto clientDto) {
        List<String> errors = new ArrayList<>();

        if (clientDto == null){
            errors.add("Veuillez entrer le nom du client");
            errors.add("Veuillez entrer le prenom du client");
            errors.add("Veuillez entrer le mail du client");
            errors.add("Veuillez entrer le numero dr telephone du client");
            return errors;
        }

        if (!StringUtils.hasLength(clientDto.getNom())) {
            errors.add("Veuillez entrer le nom du client");
        }
        if (!StringUtils.hasLength(clientDto.getPrenom())) {
            errors.add("Veuillez entrer le prenom du client");
        }
        if (!StringUtils.hasLength(clientDto.getMail())) {
            errors.add("Veuillez entrer le mail du client");
        }
        if (!StringUtils.hasLength(clientDto.getTelephone())) {
            errors.add("Veuillez entrer le numero de telephone du client");
        }
        return errors;
    }
}
