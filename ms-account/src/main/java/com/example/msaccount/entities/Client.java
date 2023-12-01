package com.example.msaccount.entities;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;

@Builder
@Data
public class Client implements Serializable {    
    private Long id;
    private String nom;
    private String prenom;
    private String email;
    private LocalDate dateNaissance;
}
