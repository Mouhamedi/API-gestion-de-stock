package com.gestionstock.Gestion.de.stock.dto;

import com.gestionstock.Gestion.de.stock.model.MvtStck;
import com.gestionstock.Gestion.de.stock.model.Roles;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RolesDto {

    private  Integer id;

    private String nomRole;

    private UtilisateurDto utilisateur;

    public static RolesDto fromEntity(Roles roles){
        if (roles==null){
            return  null;
        }
        return RolesDto.builder()
                .id(roles.getId())
                .nomRole(roles.getNomRole())
                .build();

    }
    public static Roles toEntity(RolesDto rolesDto){
        if (rolesDto==null){
            return  null;
        }
        Roles roles = new Roles();
        roles.setId(rolesDto.getId());
        roles.setNomRole(rolesDto.getNomRole());

        return roles;
    }
}
