package com.gestionstock.Gestion.de.stock.model;


import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "entreprise")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Entreprise extends AbstractEntity{
    @Column(name = "nom")
    private String nom;

    @Column(name = "secteurActivite")
    private String secteuractivite;

    @Column(name = "description")
    private String description;

    @Column(name = "idEntreprise")
    private Integer idEntreprise;

    @Column(name = "telephone")
    private String telephone;

    @Column(name = "adresse")
    private String adresse;

    @Column(name = "ville")
    private String ville;

    @Column(name = "codepostale")
    private String codePostale;

    @Column(name = "pays")
    private String pays;

    @Column(name = "mail")
    private String mail;

    @Column(name = "siteWeb")
    private String siteweb;

   @OneToMany(mappedBy = "entreprise")
   private List<Utilisateur> utilisateurs;


}
