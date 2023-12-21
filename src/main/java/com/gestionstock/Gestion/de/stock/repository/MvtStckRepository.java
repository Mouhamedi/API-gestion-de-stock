package com.gestionstock.Gestion.de.stock.repository;

import com.gestionstock.Gestion.de.stock.model.MvtStck;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MvtStckRepository extends JpaRepository<MvtStck, Integer> {
}
