package com.ynov.commerce.dao;

import com.ynov.commerce.entities.LigneDeCommande;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LigneDeCommandeRepository extends JpaRepository<LigneDeCommande, Long> {
}