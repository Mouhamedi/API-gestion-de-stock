package com.gestionstock.Gestion.de.stock.model;


import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "role")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Roles extends AbstractEntity{

    @Column(name = "nomrole")
    private String nomRole;

    @ManyToOne
    @JoinColumn(name = "idUtilisateur")
    private Utilisateur utilisateur;

    @Column(name = "idEntreprise")
    private Integer idEntreprise;
}
