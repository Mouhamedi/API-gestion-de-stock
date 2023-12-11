package com.gestionstock.Gestion.de.stock.dto;


import com.gestionstock.Gestion.de.stock.model.Article;
import com.gestionstock.Gestion.de.stock.model.Category;
import lombok.Builder;
import lombok.Data;
import java.math.BigDecimal;

@Data
@Builder
public class ArticleDto {

    private  Integer id;

    private String codeArticle;

    private String designation;

    private BigDecimal prixUnitaireHt;

    private BigDecimal tauxTVA;

    private BigDecimal prixUnitaireTte;

    private String photo;

    private CategoryDto category;

    public static ArticleDto fromEntity(Article article){
        if (article==null){
            return  null;
        }
        return ArticleDto.builder()
                .id(article.getId())
                .codeArticle(article.getCodeArticle())
                .designation(article.getDesignation())
                .prixUnitaireHt(article.getPrixUnitaireHt())
                .prixUnitaireTte(article.getPrixUnitaireTte())
                .photo(article.getPhoto())
                .category(CategoryDto.fromEntity(article.getCategory()))
                .build();
    }
    public static Article toEntity(ArticleDto articleDto){
        if (articleDto==null){
            return  null;
        }
        Article article = new Article();
        article.setId(articleDto.getId());
        article.setCodeArticle(articleDto.getCodeArticle());
        article.setDesignation(articleDto.getDesignation());
        article.setPrixUnitaireHt(articleDto.getPrixUnitaireHt());
        article.setPrixUnitaireTte(articleDto.getPrixUnitaireTte());
        article.setPhoto(articleDto.getPhoto());
       // article.setCategory(CategoryDto.fromEntity(articleDto.getCategory());

        return article;
    }
}