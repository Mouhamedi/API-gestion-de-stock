package com.gestionstock.Gestion.de.stock.controller;

import com.gestionstock.Gestion.de.stock.controller.api.ClientControllerApi;
import com.gestionstock.Gestion.de.stock.dto.ClientDto;
import com.gestionstock.Gestion.de.stock.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ClientController implements ClientControllerApi {

    private ClientService clientService;

    @Autowired
    public ClientController(
            ClientService clientService
    ) {
        this.clientService = clientService;
    }

    @Override
    public ClientDto save(ClientDto clientDto){
        return clientService.save(clientDto);
    }

    @Override
    public ClientDto findById(Integer id) {

        return clientService.findById(id);
    }


    @Override
    public List<ClientDto> findAll() {

        return clientService.findAll();
    }

    @Override
    public void delete(Integer id) {

        clientService.delete(id);
    }
}
