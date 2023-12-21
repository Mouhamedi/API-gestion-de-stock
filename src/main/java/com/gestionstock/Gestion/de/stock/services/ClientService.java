package com.gestionstock.Gestion.de.stock.services;


import com.gestionstock.Gestion.de.stock.dto.ClientDto;

import java.util.List;

public interface ClientService {

    ClientDto save(ClientDto clientDto);

    ClientDto findById(Integer id);

    List<ClientDto> findAll();

    void delete(Integer id);
}
