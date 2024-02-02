package com.gestionstock.Gestion.de.stock.model;


import javax.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

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

    @Column(name = "idEntrepriseA")
    private Integer idEntreprise;


    @Column(name = "prixunitairette")
    private BigDecimal prixUnitaireTte;

    @Column(name = "photo")
    private String photo;

   @ManyToOne
    @JoinColumn(name = "idcategory")
    private Category category;

   @OneToMany(mappedBy = "article")
   private List<LigneVentes> ligneVentes;

    @OneToMany(mappedBy = "article")
   private List<LigneCommandeClient> ligneCommandeClients;

    @OneToMany(mappedBy = "article")
   private List<LigneCommandeFournisseur> ligneCommandeFournisseurs;

}
