package com.gestionstock.Gestion.de.stock.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.*;


import java.util.List;

@Entity
@Table(name = "fournisseur")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Fournisseur extends AbstractEntity{
    @Column(nullable = false)
    private String nom;
    @Column(nullable = false)
    private String prenom;

    @Column(name = "telephone ")
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

    @Column(name = "photo")
    private String photo;

    @OneToMany(mappedBy = "fournisseur")
    private List<CommandeFournisseur> commandeFournisseurs;
}
