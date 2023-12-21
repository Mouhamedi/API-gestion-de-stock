package com.gestionstock.Gestion.de.stock.dto;

import com.gestionstock.Gestion.de.stock.model.LigneVentes;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class LigneVentesDto {

    private  Integer id;

    private VentesDto vente;

    private BigDecimal quantite;

    private BigDecimal prixUnitaire;

    public static LigneVentesDto fromEntity(LigneVentes ligneVentes){
        if (ligneVentes==null){
            return  null;
        }
        return LigneVentesDto.builder()
                .id(ligneVentes.getId())
                .quantite(ligneVentes.getQuantite())
                .prixUnitaire(ligneVentes.getPrixUnitaire())
                .vente(VentesDto.fromEntity(ligneVentes.getVente()))
                .build();

    }
    public static LigneVentes toEntity(LigneVentesDto ligneventesDto){
        if (ligneventesDto==null){
            return  null;
        }
        LigneVentes ligneVentes = new LigneVentes();
        ligneVentes.setId(ligneventesDto.getId());
        ligneVentes.setQuantite(ligneventesDto.getQuantite());
        ligneVentes.setPrixUnitaire(ligneventesDto.getPrixUnitaire());
        ligneVentes.setVente(VentesDto.toEntity(ligneventesDto.getVente()));

        return ligneVentes;
    }
}
