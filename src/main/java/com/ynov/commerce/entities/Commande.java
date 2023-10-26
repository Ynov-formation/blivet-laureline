package com.ynov.commerce.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "t_commande")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Commande {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String designation;
    private String description;
    private LocalDateTime date;

    @OneToMany(mappedBy = "commande")
    private List<LigneDeCommande> ligneDeCommandes;

}
