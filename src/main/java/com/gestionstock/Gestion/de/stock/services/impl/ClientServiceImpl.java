package com.gestionstock.Gestion.de.stock.services.impl;


import com.gestionstock.Gestion.de.stock.dto.ClientDto;
import com.gestionstock.Gestion.de.stock.exception.EntityNotFoundException;
import com.gestionstock.Gestion.de.stock.exception.ErrorCodes;
import com.gestionstock.Gestion.de.stock.exception.InvalidEntityException;
import com.gestionstock.Gestion.de.stock.model.Client;
import com.gestionstock.Gestion.de.stock.repository.ClientRepository;
import com.gestionstock.Gestion.de.stock.services.ClientService;
import com.gestionstock.Gestion.de.stock.validator.ClientValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ClientServiceImpl implements ClientService {

   private ClientRepository clientRepository;

   @Autowired
   public ClientServiceImpl(
           ClientRepository clientRepository
   ){

       this.clientRepository = clientRepository;
   }

    @Override
    public ClientDto save(ClientDto clientDto) {
       List<String> errors = ClientValidator.validate(clientDto);
       if (!errors.isEmpty()){
           log.error("Client non valide {}", clientDto);
           throw new InvalidEntityException("le client n'est pas valide", ErrorCodes.CLIENT_NOT_VALID, errors);
       }
        return ClientDto.fromEntity((clientRepository.save(ClientDto.toEntity(clientDto))));
    }

    @Override
    public ClientDto findById(Integer id) {
        if (id == null){
            log.error("L'ID du client est null");
            return null;
    }
        Optional<Client> client = clientRepository.findById(id);

        ClientDto clientDto = ClientDto.fromEntity(client.get());

        return Optional.of(clientDto).orElseThrow(() -> new EntityNotFoundException(
                "Aucun client a été trouvé avec l'ID = " + id + "dans la base de donnée",
                ErrorCodes.CATEGORY_NOT_FOUND)
    );
   }


    @Override
    public List<ClientDto> findAll() {
        return clientRepository.findAll().stream()
                .map(ClientDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Integer id) {
        if (id == null){
            log.error("L'ID du client est null");
            return ;
        }
        clientRepository.deleteById(id);

    }
}
