package com.gestionstock.Gestion.de.stock.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;


@Entity
@Table(name = "clients")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Client extends AbstractEntity{

    @Column(nullable = false)
    private String nom;
    @Column(nullable = false)
    private String prenom;

    @Column(name = "client_telephone")
    private String telephone;

    @Column(name = "idEntreprise")
    private Integer idEntreprise;

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

   @OneToMany(mappedBy = "client")
   private List<CommandeClient> commandeClients;

}
