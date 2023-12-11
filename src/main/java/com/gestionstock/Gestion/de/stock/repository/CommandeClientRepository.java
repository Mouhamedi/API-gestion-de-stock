package com.gestionstock.Gestion.de.stock.repository;

import com.gestionstock.Gestion.de.stock.model.CommandeClient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommandeClientRepository extends JpaRepository<Integer, CommandeClient> {
}
