package com.gestionstock.Gestion.de.stock.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

  //permet de construire des objet avec des methode de meme nom
@Entity
@Table(name = "article")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)

public class Article extends AbstractEntity {

    @Column(name = "codearticle")
    private String codeArticle;

    @Column(name = "designation")
    private String designation;

    @Column(name = "prixunitaireht")
    private BigDecimal prixUnitaireHt;

    @Column(name = "tauxTva")
    private BigDecimal tauxTVA;


    @Column(name = "prixunitairette")
    private BigDecimal prixUnitaireTte;

    @Column(name = "photo")
    private String photo;

   @ManyToOne
    @JoinColumn(name = "idcategory")
    private Category category;
}
