package com.ynov.commerce.dto;
import lombok.*;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CategorieDto {
    Long id;
    String nom;
    String description;
}