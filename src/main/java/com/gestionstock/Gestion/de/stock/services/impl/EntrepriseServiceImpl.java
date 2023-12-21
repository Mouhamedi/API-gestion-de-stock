package com.gestionstock.Gestion.de.stock.services.impl;


import com.gestionstock.Gestion.de.stock.dto.EntrepriseDto;
import com.gestionstock.Gestion.de.stock.exception.EntityNotFoundException;
import com.gestionstock.Gestion.de.stock.exception.ErrorCodes;
import com.gestionstock.Gestion.de.stock.exception.InvalidEntityException;
import com.gestionstock.Gestion.de.stock.model.Entreprise;
import com.gestionstock.Gestion.de.stock.repository.EntrepriseRepository;
import com.gestionstock.Gestion.de.stock.services.EntrepriseService;
import com.gestionstock.Gestion.de.stock.validator.EntrepriseValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class EntrepriseServiceImpl implements EntrepriseService {


    public EntrepriseRepository entrepriseRepository;

    @Autowired
    public EntrepriseServiceImpl(EntrepriseRepository entrepriseRepository){

        this.entrepriseRepository = entrepriseRepository;
    }

    @Override
    public EntrepriseDto save(EntrepriseDto entrepriseDto) {
        List<String> errors = EntrepriseValidator.validate(entrepriseDto);
        if (!errors.isEmpty()){
            log.error("Entreprise non valid {}", entrepriseDto);
            throw new InvalidEntityException("l'entreprise n'est pas valide", ErrorCodes.ENTREPRISE_NOT_VALID, errors);
        }
        return EntrepriseDto.fromEntity((entrepriseRepository.save(EntrepriseDto.toEntity(entrepriseDto))));
    }


    @Override
    public EntrepriseDto findById(Integer id) {
        if (id == null){
            log.error("L'ID de l'entreprise est null");
            return null;
        }
        Optional<Entreprise> entreprise = entrepriseRepository.findById(id);

        EntrepriseDto entrepriseDto = EntrepriseDto.fromEntity(entreprise.get());

        return Optional.of(entrepriseDto).orElseThrow(() -> new EntityNotFoundException(
                "Aucune entreprise a été trouvé avec l'ID = " + id + "dans la base de donnée",
                ErrorCodes.ENTREPRISE_NOT_FOUND)
        );
    }


    @Override
    public List<EntrepriseDto> findAll() {
        return entrepriseRepository.findAll().stream()
                .map(EntrepriseDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Integer id) {
        if (id == null){
            log.error("L'ID de l'entreprise est null");
            return ;
        }
        entrepriseRepository.deleteById(id);
    }
}
