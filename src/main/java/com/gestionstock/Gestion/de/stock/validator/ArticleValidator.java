package com.gestionstock.Gestion.de.stock.validator;

import com.gestionstock.Gestion.de.stock.dto.ArticleDto;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class ArticleValidator {
    public static List<String> validate(ArticleDto articleDto){
        List<String> errors = new ArrayList<>();

        if (articleDto == null){
            errors.add("Veuillez entrer le code d'article");
            errors.add("Veuillez entrer la designation d'article");
            errors.add("Veuillez entrer la designation d'article");
            errors.add("Veuillez entrer le prix unitaire HT d'article");
            errors.add("Veuillez entrer le taux TVA d'article");
            errors.add("Veuillez entrer le prixTTC  d'article");
            errors.add("Veuillez selectionner une categorie");
            return errors;
        }

        if (!StringUtils.hasLength(articleDto.getCodeArticle())){
            errors.add("Veuillez entrer le code d'article");
        }
        if (!StringUtils.hasLength(articleDto.getDesignation())){
            errors.add("Veuillez entrer la designation d'article");
        }
        if (articleDto.getPrixUnitaireHt() == null){
            errors.add("Veuillez entrer le prix unitaire HT d'article");
        }
        if (articleDto.getTauxTVA() == null){
            errors.add("Veuillez entrer le taux TVA d'article");
        }
        if (articleDto.getPrixUnitaireTte() == null){
            errors.add("Veuillez entrer le prixTTC  d'article");
        }
        if (articleDto.getCategory() == null){
            errors.add("Veuillez selectionner une categorie");
        }
        return errors;
    }
}
