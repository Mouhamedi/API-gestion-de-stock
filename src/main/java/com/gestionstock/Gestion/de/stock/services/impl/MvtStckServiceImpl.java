package com.gestionstock.Gestion.de.stock.services.impl;

import com.gestionstock.Gestion.de.stock.dto.MvtStckDto;
import com.gestionstock.Gestion.de.stock.exception.EntityNotFoundException;
import com.gestionstock.Gestion.de.stock.exception.ErrorCodes;
import com.gestionstock.Gestion.de.stock.exception.InvalidEntityException;
import com.gestionstock.Gestion.de.stock.model.MvtStck;
import com.gestionstock.Gestion.de.stock.repository.ArticleRepository;
import com.gestionstock.Gestion.de.stock.repository.MvtStckRepository;
import com.gestionstock.Gestion.de.stock.services.MvtStckService;
import com.gestionstock.Gestion.de.stock.validator.MvtStckValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class MvtStckServiceImpl implements MvtStckService {

    private MvtStckRepository mvtStckRepository;
    private ArticleRepository articleRepository;

    @Autowired
    public MvtStckServiceImpl(
            MvtStckRepository mvtStckRepository,
            ArticleRepository articleRepository

    ) {
        this.mvtStckRepository = mvtStckRepository;
        this.articleRepository = articleRepository;
    }

    @Override
    public MvtStckDto save(MvtStckDto mvtStckDto) {
        List<String> errors = MvtStckValidator.validate(mvtStckDto);
        if (!errors.isEmpty()){
            log.error("Le stock n'est pas valide");
            throw new InvalidEntityException("Stock non valide", ErrorCodes.MVT_STCK_NOT_VALID,errors);
        }

        return MvtStckDto.fromEntity(mvtStckRepository.save(MvtStckDto.toEntity(mvtStckDto)));
    }

    @Override
    public MvtStckDto findById(Integer id) {
        if (id == null){
            log.error("L'ID du stock est null");
            return null;
        }
        Optional<MvtStck> mvtStck = mvtStckRepository.findById(id);

        MvtStckDto mvtStckDto = MvtStckDto.fromEntity(mvtStck.get());

        return Optional.of(mvtStckDto).orElseThrow(() -> new EntityNotFoundException(
                "Aucun mouvement de stock n'a été trouvé avec l'ID" + id + "dans la base de donnée",
                ErrorCodes.MVT_STCK_NOT_FOUND)
        );
    }

    @Override
    public List<MvtStckDto> findAll() {
        return mvtStckRepository.findAll().stream()
                .map(MvtStckDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Integer id) {
        if (id == null){
            log.error("L'ID du client est null");
            return ;
        }
        mvtStckRepository.deleteById(id);

    }
}
