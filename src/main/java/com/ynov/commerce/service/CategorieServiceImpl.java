package com.ynov.commerce.service;

import com.ynov.commerce.dao.CategorieRepository;
import com.ynov.commerce.dto.CategorieDto;
import com.ynov.commerce.entities.Categorie;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
@Service
@Transactional
public class CategorieServiceImpl implements CategorieService{

    private CategorieRepository categorieRepository;

    public CategorieServiceImpl(CategorieRepository categorieRepository) {
        this.categorieRepository = categorieRepository;
    }

    @Override
    public Categorie saveCategorie(Categorie categorie) {
        return categorieRepository.save(categorie);
    }

    @Override
    public Categorie updateCategorie(Categorie categorie) {
        return saveCategorie(categorie);
    }

    @Override
    public void deleteCategorie(Long id) {
        categorieRepository.deleteById(id);

    }

    @Override
    public List<CategorieDto> getAllCategories() {
        List<CategorieDto> toReturn = new ArrayList<>();
        categorieRepository.findAll().forEach(categorie -> {
           CategorieDto categoryDto = CategorieDto.builder()
                   .id(categorie.getId())
                   .nom(categorie.getNom())
                   .description(categorie.getDescription())
                   .build();
           toReturn.add(categoryDto);
        });
        return toReturn;
    }
}
