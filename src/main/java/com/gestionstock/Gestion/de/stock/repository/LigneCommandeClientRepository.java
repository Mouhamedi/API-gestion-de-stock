package com.gestionstock.Gestion.de.stock.repository;

import com.gestionstock.Gestion.de.stock.model.LigneCommandeClient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;

public interface LigneCommandeClientRepository extends JpaRepository<LigneCommandeClient, Integer> {
    Collection<Object> findByCommandeClientId(Integer idCommande);
}
