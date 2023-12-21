package com.gestionstock.Gestion.de.stock.services.impl;

import com.gestionstock.Gestion.de.stock.dto.FournisseurDto;
import com.gestionstock.Gestion.de.stock.exception.EntityNotFoundException;
import com.gestionstock.Gestion.de.stock.exception.ErrorCodes;
import com.gestionstock.Gestion.de.stock.exception.InvalidEntityException;
import com.gestionstock.Gestion.de.stock.model.Fournisseur;
import com.gestionstock.Gestion.de.stock.repository.FournisseurRepository;
import com.gestionstock.Gestion.de.stock.services.FournisseurService;
import com.gestionstock.Gestion.de.stock.validator.FournisseurValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class FournisseurServiceImpl implements FournisseurService {

    private FournisseurRepository fournisseurRepository;

    @Autowired
    public FournisseurServiceImpl(
            FournisseurRepository fournisseurRepository
    ){
        this.fournisseurRepository = fournisseurRepository;
    }

    @Override
    public FournisseurDto save(FournisseurDto fournisseurDto) {
        List<String> errors = FournisseurValidator.validate(fournisseurDto);
        if (!errors.isEmpty()){
            log.error("Fournisseur non valide {}", fournisseurDto);
            throw new InvalidEntityException("Le fournisseur n'est pas valide ", ErrorCodes.COMMANDE_FOURNISSEUR_NOT_VALID, errors);

        }
        return FournisseurDto.fromEntity(fournisseurRepository.save(FournisseurDto.toEntity(fournisseurDto)));
    }

    @Override
    public FournisseurDto findById(Integer id) {
            if (id == null){
                log.error("L'ID du fournisseur est null");
                return null;
            }
            Optional<Fournisseur> fournisseur = fournisseurRepository.findById(id);

            FournisseurDto fournisseurDto = FournisseurDto.fromEntity(fournisseur.get());

            return Optional.of(fournisseurDto).orElseThrow(() -> new EntityNotFoundException(
                    "Aucun fournisseur a été trouvé avec l'ID = " + id + "dans la base de donnée",
                    ErrorCodes.FOURNISSEUR_NOT_FOUND)
            );
    }


    @Override
    public List<FournisseurDto> findAll() {
        return fournisseurRepository.findAll().stream()
                .map(FournisseurDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Integer id) {
        if (id == null){
            log.error("L'ID du fournisseur est null");
            return ;
        }
        fournisseurRepository.deleteById(id);


    }
}
