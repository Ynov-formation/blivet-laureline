package com.example.msoperation.entities;

import java.io.Serializable;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Account implements Serializable {    
    private Long id;
    private String nom;
    private String description;
    private double montant;  
    private Long clientId;     
}
