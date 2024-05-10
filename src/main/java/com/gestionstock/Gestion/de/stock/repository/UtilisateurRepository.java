package com.gestionstock.Gestion.de.stock.repository;

import com.gestionstock.Gestion.de.stock.model.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UtilisateurRepository extends JpaRepository<Utilisateur, Integer> {

    Optional<Utilisateur> findUtilisateurByMail(String mail);

}
