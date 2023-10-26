package com.ynov.commerce.dto;
import lombok.*;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProduitDto implements Serializable {
    Long id;
    String nom;
    String description;
    double prix;
    int quantite;
    String image;
    CategorieDto categorie;
}