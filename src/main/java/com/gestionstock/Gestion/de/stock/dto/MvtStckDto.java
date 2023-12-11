package com.gestionstock.Gestion.de.stock.dto;

import com.gestionstock.Gestion.de.stock.model.LigneVentes;
import com.gestionstock.Gestion.de.stock.model.MvtStck;
import com.gestionstock.Gestion.de.stock.model.TypeMvtStck;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.Instant;

@Data
@Builder
public class MvtStckDto {

    private  Integer id;

    private Instant dateMvt;

    private BigDecimal quantite;

    private ArticleDto article;

    private TypeMvtStck typeMvtStck;

    public static MvtStckDto fromEntity(MvtStck mvtStck){
        if (mvtStck==null){
            return  null;
        }
        return MvtStckDto.builder()
                .id(mvtStck.getId())
                .dateMvt(mvtStck.getDateMvt())
                .quantite(mvtStck.getQuantite())
                .build();

    }
    public static MvtStck toEntity(MvtStckDto mvtStckDto){
        if (mvtStckDto==null){
            return  null;
        }
        MvtStck mvtStck = new MvtStck();
        mvtStck.setId(mvtStckDto.getId());
        mvtStck.setDateMvt(mvtStckDto.getDateMvt());
        mvtStck.setQuantite(mvtStck.getQuantite());

        return mvtStck;
    }
}
