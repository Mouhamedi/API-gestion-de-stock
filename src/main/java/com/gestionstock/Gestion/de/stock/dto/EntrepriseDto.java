package com.gestionstock.Gestion.de.stock.dto;

import com.gestionstock.Gestion.de.stock.model.Client;
import com.gestionstock.Gestion.de.stock.model.Entreprise;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class EntrepriseDto {

    private  Integer id;

    private String nom;

    private String secteuractivite;

    private String description;

    private String telephone;

    private String adresse;

    private String ville;

    private String codePostale;

    private String pays;

    private String mail;

    private String siteweb;

    private List<UtilisateurDto> utilisateurs;

    public static EntrepriseDto fromEntity(Entreprise entreprise){
        if (entreprise==null){
            return  null;
        }
        return EntrepriseDto.builder()
                .id(entreprise.getId())
                .nom(entreprise.getNom())
                .secteuractivite(entreprise.getSecteuractivite())
                .telephone(entreprise.getTelephone())
                .adresse(entreprise.getAdresse())
                .ville(entreprise.getVille())
                .codePostale(entreprise.getCodePostale())
                .pays(entreprise.getPays())
                .mail(entreprise.getMail())
                .siteweb(entreprise.getSiteweb())
                .build();
    }
    public static Entreprise toEntity(EntrepriseDto entrepriseDto){
        if (entrepriseDto==null){
            return  null;
        }
        Entreprise entreprise = new Entreprise();
        entreprise.setId(entrepriseDto.getId());
        entreprise.setNom(entrepriseDto.getNom());
        entreprise.setSecteuractivite(entreprise.getSecteuractivite());
        entreprise.setDescription(entrepriseDto.getDescription());
        entreprise.setTelephone(entrepriseDto.getTelephone());
        entreprise.setAdresse(entrepriseDto.getAdresse());
        entreprise.setVille(entrepriseDto.getVille());
        entreprise.setCodePostale(entrepriseDto.getCodePostale());
        entreprise.setPays(entrepriseDto.getPays());
        entreprise.setMail(entrepriseDto.getMail());
        entreprise.setSiteweb(entrepriseDto.getSiteweb());

        return entreprise;
    }
}
