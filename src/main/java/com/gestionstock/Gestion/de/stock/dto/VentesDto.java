package com.gestionstock.Gestion.de.stock.dto;


import com.gestionstock.Gestion.de.stock.model.Ventes;
import lombok.Builder;
import lombok.Data;

import java.time.Instant;
import java.util.List;

@Data
@Builder
public class VentesDto {

    private  Integer id;

    private String code;

    private Instant dateVente;

    private String commentaire;

    private List<LigneVentesDto> ligneVentesDto;

    public static VentesDto fromEntity(Ventes ventes){
        if (ventes==null){
            return  null;
        }
        return VentesDto.builder()
                .id(ventes.getId())
                .code(ventes.getCode())
                .dateVente(ventes.getDateVente())
                .commentaire(ventes.getCommentaire())
                .build();

    }
    public static Ventes toEntity(VentesDto ventesDto){
        if (ventesDto==null){
            return  null;
        }
        Ventes ventes = new Ventes();
        ventes.setId(ventesDto.getId());
        ventes.setCode(ventesDto.getCode());
        ventes.setDateVente(ventesDto.getDateVente());
        ventes.setCommentaire(ventesDto.getCommentaire());

        return ventes;
    }
}
