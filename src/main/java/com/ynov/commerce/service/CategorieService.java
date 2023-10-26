package com.ynov.commerce.service;

import com.ynov.commerce.dto.CategorieDto;
import com.ynov.commerce.entities.Categorie;

import java.util.List;

public interface CategorieService {
    Categorie saveCategorie(Categorie categorie);
    Categorie updateCategorie(Categorie categorie);
    void deleteCategorie(Long id);
    List<CategorieDto> getAllCategories();

}
