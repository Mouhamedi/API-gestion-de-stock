package com.gestionstock.Gestion.de.stock.dto;

import com.gestionstock.Gestion.de.stock.model.Client;
import com.gestionstock.Gestion.de.stock.model.Fournisseur;
import lombok.Builder;
import lombok.Data;
import java.util.List;

@Data
@Builder
public class FournisseurDto {

    private  Integer id;

    private String nom;

    private String prenom;

    private String telephone;

    private String adresse;

    private String ville;

    private String codePostale;

    private String pays;

    private String mail;

    private String photo;

    private List<CommandeFournisseurDto> commandeFournisseurs;

    public static FournisseurDto fromEntity(Fournisseur fournisseur){
        if (fournisseur==null){
            return  null;
        }
        return FournisseurDto.builder()
                .id(fournisseur.getId())
                .nom(fournisseur.getNom())
                .prenom(fournisseur.getPrenom())
                .telephone(fournisseur.getTelephone())
                .adresse(fournisseur.getAdresse())
                .ville(fournisseur.getVille())
                .codePostale(fournisseur.getCodePostale())
                .pays(fournisseur.getPays())
                .mail(fournisseur.getMail())
                .photo(fournisseur.getPhoto())
                .build();
    }
    public static Fournisseur toEntity(FournisseurDto fournisseurDto){
        if (fournisseurDto==null){
            return  null;
        }
        Fournisseur fournisseur = new Fournisseur();
        fournisseur.setId(fournisseurDto.getId());
        fournisseur.setNom(fournisseurDto.getNom());
        fournisseur.setPrenom(fournisseurDto.getPrenom());
        fournisseur.setTelephone(fournisseurDto.getTelephone());
        fournisseur.setAdresse(fournisseurDto.getAdresse());
        fournisseur.setVille(fournisseurDto.getVille());
        fournisseur.setCodePostale(fournisseurDto.getCodePostale());
        fournisseur.setPays(fournisseurDto.getPays());
        fournisseur.setMail(fournisseurDto.getMail());
        fournisseur.setPhoto(fournisseurDto.getPhoto());

        return fournisseur;
    }
}
