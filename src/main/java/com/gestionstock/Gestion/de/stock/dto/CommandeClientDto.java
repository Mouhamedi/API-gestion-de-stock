package com.gestionstock.Gestion.de.stock.dto;


import com.gestionstock.Gestion.de.stock.model.CommandeClient;
import com.gestionstock.Gestion.de.stock.model.EtatCommande;
import lombok.Builder;
import lombok.Data;

import java.time.Instant;
import java.util.List;

@Data
@Builder
public class CommandeClientDto {
// les attributs pour client
    private  Integer id;

    private String code;

    private Instant dateCommande;

    private EtatCommande etatCommande;

    private  ClientDto client;

    private List<LigneCommandeClientDto> ligneCommandeClients;

    public static CommandeClientDto fromEntity(CommandeClient commandeClient){
        if (commandeClient==null){
            return  null;
        }
        return CommandeClientDto.builder()
                .id(commandeClient.getId())
                .code(commandeClient.getCode())
                .dateCommande(commandeClient.getDateCommande())
                .etatCommande(commandeClient.getEtatCommande())
                .client(ClientDto.fromEntity(commandeClient.getClient()))
                .build();

    }
    public static CommandeClient toEntity(CommandeClientDto commandeClientDto){
        if (commandeClientDto==null){
            return  null;
        }
        CommandeClient commandeClient = new CommandeClient();
        commandeClient.setId(commandeClientDto.getId());
        commandeClient.setCode(commandeClientDto.getCode());
        commandeClient.setDateCommande(commandeClientDto.getDateCommande());
        commandeClient.setEtatCommande(commandeClientDto.getEtatCommande() );
        commandeClient.setClient(ClientDto.toEntity(commandeClientDto.getClient()));

        return commandeClient;
    }

    public boolean isCommandeLivree() {
        return EtatCommande.LIVRE.equals(this.etatCommande);
    }
}
