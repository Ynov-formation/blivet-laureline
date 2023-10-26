package com.ynov.commerce.entities;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;

@Entity
@Table(name = "t__ligne_commande")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LigneDeCommande {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private int quantite;
    private double prixUnitaire;
    private String designation;

    @ManyToOne
    private Commande commande;

    @OneToOne
    private Produit produit;


}
