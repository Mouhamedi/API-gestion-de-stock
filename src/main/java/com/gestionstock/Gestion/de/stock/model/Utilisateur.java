package com.gestionstock.Gestion.de.stock.model;


import lombok.*;

import javax.persistence.*;
import java.time.Instant;
import java.util.List;

@Entity
@Table(name = "utilisateur")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Utilisateur extends Article{

    @Column(name = "nom")
    private String nom;
    @Column(name = "prenom")
    private String prenom;

    @Column(name = "telephone")
    private String telephone;


    @Column(name = "mail")
    private String mail;

    @Column(name = "dateDeNaissance")
    private Instant dateDeNaissance;

    @Column(name = "motDePasse")
    private String motDePasse;

    @Column(name = "adresse")
    private String adresse;
    @Column(name = "ville")
    private String ville;

    @Column(name = "codepostale")
    private String codePostale;

    @Column(name = "pays")
    private String pays;

    @Column(name = "photo")
    private String photo;

    @ManyToOne
    @JoinColumn(name = "idEntreprise")
    private Entreprise entreprise;

    @OneToMany(mappedBy = "utilisateur")
    private List<Roles> roles;

}
