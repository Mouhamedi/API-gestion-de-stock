package com.gestionstock.Gestion.de.stock.services.impl;

import com.gestionstock.Gestion.de.stock.dto.CommandeClientDto;
import com.gestionstock.Gestion.de.stock.dto.LigneCommandeClientDto;
import com.gestionstock.Gestion.de.stock.exception.EntityNotFoundException;
import com.gestionstock.Gestion.de.stock.exception.ErrorCodes;
import com.gestionstock.Gestion.de.stock.exception.InvalidEntityException;
import com.gestionstock.Gestion.de.stock.model.Article;
import com.gestionstock.Gestion.de.stock.model.Client;
import com.gestionstock.Gestion.de.stock.model.CommandeClient;
import com.gestionstock.Gestion.de.stock.model.LigneCommandeClient;
import com.gestionstock.Gestion.de.stock.repository.ArticleRepository;
import com.gestionstock.Gestion.de.stock.repository.ClientRepository;
import com.gestionstock.Gestion.de.stock.repository.CommandeClientRepository;
import com.gestionstock.Gestion.de.stock.repository.LigneCommandeClientRepository;
import com.gestionstock.Gestion.de.stock.services.CommandeClientService;
import com.gestionstock.Gestion.de.stock.validator.CommandeClientValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class CommandeClientServiceImpl implements CommandeClientService {

    private CommandeClientRepository commandeClientRepository;
    private LigneCommandeClientRepository ligneCommandeClientRepository;
    private ClientRepository clientRepository;
    private ArticleRepository articleRepository;

    public CommandeClientServiceImpl(
            CommandeClientRepository commandeClientRepository,
            LigneCommandeClientRepository ligneCommandeClientRepository,
            ClientRepository clientRepository,
            ArticleRepository articleRepository
    ) {
        this.commandeClientRepository = commandeClientRepository;
        this.ligneCommandeClientRepository = ligneCommandeClientRepository;
        this.clientRepository = clientRepository;
        this.articleRepository = articleRepository;
    }


    @Override
    public CommandeClientDto save(CommandeClientDto commandeClientDto) {
        List<String> errors = CommandeClientValidator.validate(commandeClientDto);

        if (!errors.isEmpty()){
            log.error("La commande du client n'est pas valide");
            throw new InvalidEntityException("la commande du client 'est pas valide", ErrorCodes.COMMANDE_CLIENT_NOT_VALID, errors);
        }

        Optional<Client> client = clientRepository.findById(commandeClientDto.getClient().getId());
        if (!client.isEmpty()){
            log.warn("L'ID {} du client n'est pas trouvé dans la base de donnée", commandeClientDto.getClient().getId());
            throw  new EntityNotFoundException(
                    "Aucun client n'a été trouvé avec l'ID = " + commandeClientDto.getClient().getId() + "dans la base de donnée", ErrorCodes.CLIENT_NOT_FOUND);
        }

        List<String> articleErrors = new ArrayList<>();

        if (commandeClientDto.getLigneCommandeClients() != null) {
            commandeClientDto.getLigneCommandeClients().forEach(ligCmdClt -> {
                if (ligCmdClt.getArticle().getId() != null) {
                    Optional<Article> article = articleRepository.findById(ligCmdClt.getArticle().getId());
                    if (article.isEmpty()) {
                        articleErrors.add("L'article avec l'ID" + ligCmdClt.getArticle().getId() + "n'existe pas");
                    } else {
                        articleErrors.add("Impossible d'enregistrer une commande avec un  article NULL");
                    }
                }
            });
        }
        if (!articleErrors.isEmpty()){
            log.warn("");
            throw new InvalidEntityException("L'article n'existe pas danss la base de donnée", ErrorCodes.ARTICLE_NOT_FOUND, articleErrors);
        }


        CommandeClient savedCmdClt = commandeClientRepository.save(CommandeClientDto.toEntity(commandeClientDto));
        if (commandeClientDto.getLigneCommandeClients() != null){
            commandeClientDto.getLigneCommandeClients().forEach(ligCmdClt -> {
                LigneCommandeClient ligneCommandeClient = LigneCommandeClientDto.toEntity(ligCmdClt);
                ligneCommandeClient.setCommandeClient(savedCmdClt);
                ligneCommandeClientRepository.save(ligneCommandeClient);

            });
        }

        return CommandeClientDto.fromEntity(savedCmdClt);
    }

    @Override
    public CommandeClientDto findById(Integer id) {
        if (id == null){
            log.error("l'ID de la Commande du client est null");
            return null;
        }
        return commandeClientRepository.findById(id)
                .map(CommandeClientDto::fromEntity)
                .orElseThrow(() -> new EntityNotFoundException(
                     "Aucune commande du client n'a été trouvé avec l'ID " + id  , ErrorCodes.COMMANDE_CLIENT_NOT_FOUND
                ));
    }

    @Override
    public CommandeClientDto findByCode(String code) {
        if (!StringUtils.hasLength(code)){
            log.error("le CODE de la Commande du client est null");
            return null;
        }
        return commandeClientRepository.findCommadeClientByCode(code)
                .map(CommandeClientDto::fromEntity)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Aucune commande du client n'a été trouvé avec le CODE " + code , ErrorCodes.COMMANDE_CLIENT_NOT_FOUND
                ));
    }

    @Override
    public List<CommandeClientDto> findAll() {
        return commandeClientRepository.findAll().stream()
                .map(CommandeClientDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Integer id) {
        if (id == null){
            log.error("l'ID de la Commande du client est null");
            return;
        }
        commandeClientRepository.deleteById(id);
    }
}
