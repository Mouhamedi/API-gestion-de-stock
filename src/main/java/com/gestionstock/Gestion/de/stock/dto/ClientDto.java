package com.gestionstock.Gestion.de.stock.dto;

import com.gestionstock.Gestion.de.stock.model.Client;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ClientDto {

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


    private List<CommandeClientDto> commandeClients;

    public static ClientDto fromEntity(Client client){
        if (client==null){
            return  null;
        }
        return ClientDto.builder()
                .id(client.getId())
                .nom(client.getNom())
                .prenom(client.getPrenom())
                .telephone(client.getTelephone())
                .adresse(client.getAdresse())
                .ville(client.getVille())
                .codePostale(client.getCodePostale())
                .pays(client.getPays())
                .mail(client.getMail())
                .photo(client.getPhoto())
                .build();
    }
    public static Client toEntity(ClientDto clientDto){
        if (clientDto==null){
            return  null;
        }
        Client client = new Client();
        client.setId(clientDto.getId());
        client.setNom(clientDto.getNom());
        client.setPrenom(clientDto.getPrenom());
        client.setTelephone(clientDto.getTelephone());
        client.setAdresse(clientDto.getAdresse());
        client.setVille(clientDto.getVille());
        client.setCodePostale(clientDto.getCodePostale());
        client.setPays(clientDto.getPays());
        client.setMail(clientDto.getMail());
        client.setPhoto(clientDto.getPhoto());

        return client;
    }
}
