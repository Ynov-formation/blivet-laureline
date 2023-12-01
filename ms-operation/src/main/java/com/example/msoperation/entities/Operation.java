package com.example.msoperation.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "t_operation")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Operation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String type; // Type d'opération (débit, crédit, virement, etc.)
    private double amount; // Montant de l'opération
    private LocalDateTime date; // Date de l'opération    
    @Transient
    private Account accountSource; // Compte source de l'opération
    private Long idAccountSource; 
    @Transient
    private Account accountDestination; // Compte destination de l'opération
    private Long idAccountDestination;

}
