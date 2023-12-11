package com.gestionstock.Gestion.de.stock.dto;

import com.gestionstock.Gestion.de.stock.model.LigneCommandeFournisseur;
import com.gestionstock.Gestion.de.stock.model.LigneVentes;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class LigneventesDto {

    private  Integer id;

    private VentesDto vente;

    private BigDecimal quantite;

    private BigDecimal prixUnitaire;

    public static LigneventesDto fromEntity(LigneVentes ligneVentes){
        if (ligneVentes==null){
            return  null;
        }
        return LigneventesDto.builder()
                .id(ligneVentes.getId())
                .quantite(ligneVentes.getQuantite())
                .prixUnitaire(ligneVentes.getPrixUnitaire())
                .build();

    }
    public static LigneVentes toEntity(LigneventesDto ligneventesDto){
        if (ligneventesDto==null){
            return  null;
        }
        LigneVentes ligneVentes = new LigneVentes();
        ligneVentes.setId(ligneventesDto.getId());
        ligneVentes.setQuantite(ligneventesDto.getQuantite());
        ligneVentes.setPrixUnitaire(ligneventesDto.getPrixUnitaire());

        return ligneVentes;
    }
}
