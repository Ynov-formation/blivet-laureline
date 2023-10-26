package com.ynov.commerce.web;

import com.ynov.commerce.dto.CategorieDto;
import com.ynov.commerce.entities.Categorie;
import com.ynov.commerce.service.CategorieService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/commerce")
public class CategorieRestRessources {

    private CategorieService categorieService;

    public CategorieRestRessources(CategorieService categorieService) {
        this.categorieService = categorieService;
    }

    @GetMapping(value = "/categories")
    public List<CategorieDto> getAllCategies() {
        return categorieService.getAllCategories();
    }
}
