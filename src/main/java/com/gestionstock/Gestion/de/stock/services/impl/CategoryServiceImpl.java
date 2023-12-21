package com.gestionstock.Gestion.de.stock.services.impl;

import com.gestionstock.Gestion.de.stock.dto.CategoryDto;
import com.gestionstock.Gestion.de.stock.exception.EntityNotFoundException;
import com.gestionstock.Gestion.de.stock.exception.ErrorCodes;
import com.gestionstock.Gestion.de.stock.exception.InvalidEntityException;
import com.gestionstock.Gestion.de.stock.model.Category;
import com.gestionstock.Gestion.de.stock.repository.CategoryRepository;
import com.gestionstock.Gestion.de.stock.services.CategoryService;
import com.gestionstock.Gestion.de.stock.validator.CategoryValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class CategoryServiceImpl implements CategoryService {

    private CategoryRepository categoryRepository;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository){
        this.categoryRepository = categoryRepository;
    }

    @Override
    public CategoryDto save(CategoryDto categoryDto){

        List<String> errors = CategoryValidator.validate(categoryDto);
        if (!errors.isEmpty()){
            log.error("Category non valide {}", categoryDto);
            throw new InvalidEntityException("La category n'est pas valide", ErrorCodes.CATEGORY_NOT_VALID, errors);
        }
        return CategoryDto.fromEntity(categoryRepository.save(CategoryDto.toEntity(categoryDto)));
    }

    @Override
    public CategoryDto findById(Integer id){
        if (id == null){
            log.error("L'ID de la categorie est null");
            return  null;
        }

        Optional<Category> category = categoryRepository.findById(id);

        CategoryDto categoryDto = CategoryDto.fromEntity(category.get());

        return Optional.of(categoryDto).orElseThrow(() -> new EntityNotFoundException(
                "Aucune categorie a été trouvé avec l'ID =" + id + "dans la base de donnée",
                ErrorCodes.CATEGORY_NOT_FOUND)
        );
    }

    @Override
    public CategoryDto findByCode(String code){

        if (!StringUtils.hasLength(code)){
            log.error("Le CODE de la categorie est null");
            return null;
        }
        Optional<Category>category = categoryRepository.findCategoryByCode(code);

        CategoryDto categoryDto = CategoryDto.fromEntity(category.get());

        return Optional.of(categoryDto).orElseThrow(() -> new EntityNotFoundException(
                "Aucune categorie n'a été trouvé avec le code = " + code + "dans la base de donnée",
                ErrorCodes.CATEGORY_NOT_FOUND)
        );
    }

    @Override
    public List<CategoryDto> findAll(){
        return categoryRepository.findAll().stream()
                .map(CategoryDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Integer id){
        if (id == null){
            log.error("L'ID de la categoerie est null");
            return;
        }
        categoryRepository.deleteById(id);
    }

}
