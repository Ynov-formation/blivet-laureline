package com.ynov.commerce.service;

import com.ynov.commerce.dao.ProduitRepository;
import com.ynov.commerce.dto.CategorieDto;
import com.ynov.commerce.dto.ProduitDto;
import com.ynov.commerce.entities.Produit;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
@Service
@Transactional
public class ProduitServiceImpl implements ProduitService{

    private ProduitRepository produitRepository;

    public ProduitServiceImpl(ProduitRepository produitRepository) {
        this.produitRepository = produitRepository;
    }

    @Override
    public Produit saveProduit(Produit produit) {
        return produitRepository.save(produit);
    }

    @Override
    public Produit updateProduit(Produit produit) {
        return saveProduit(produit);
    }

    @Override
    public void deleteProduit(Long id) {
        produitRepository.deleteById(id);

    }

    @Override
    public List<ProduitDto> getAllProduits() {  
        List<Produit> produits = produitRepository.findAll();
        List<ProduitDto> produitsDto = new ArrayList<>();
        for (Produit produit : produits) {
            produitsDto.add(ProduitDto.builder()
                    .id(produit.getId())
                    .nom(produit.getNom())
                    .description(produit.getDescription())
                    .prix(produit.getPrix())
                    .quantite(produit.getQuantite())
                    .image(produit.getImage())
                    .categorie(CategorieDto.builder()
                            .id(produit.getCategorie().getId())
                            .nom(produit.getCategorie().getNom())
                            .description(produit.getCategorie().getDescription())
                            .build())
                    .build());
        }
        return produitsDto;
    }

    
}
