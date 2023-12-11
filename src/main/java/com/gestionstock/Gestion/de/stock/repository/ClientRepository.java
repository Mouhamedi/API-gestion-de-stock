package com.gestionstock.Gestion.de.stock.repository;

import com.gestionstock.Gestion.de.stock.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Integer, Client> {
}
