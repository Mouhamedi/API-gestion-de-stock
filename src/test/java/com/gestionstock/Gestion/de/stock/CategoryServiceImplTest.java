package com.gestionstock.Gestion.de.stock;

import com.gestionstock.Gestion.de.stock.dto.CategoryDto;
import com.gestionstock.Gestion.de.stock.exception.EntityNotFoundException;
import com.gestionstock.Gestion.de.stock.exception.ErrorCodes;
import com.gestionstock.Gestion.de.stock.exception.InvalidEntityException;
import com.gestionstock.Gestion.de.stock.services.CategoryService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CategoryServiceImplTest {

    @Autowired
    private CategoryService categoryService;

    @Test
    public void shouldSaveCategoryWithSuccess() {
        CategoryDto expectedCategory = CategoryDto.builder()
                .code("Cat test")
                .designation("Designation test")
                //.idEntreprise(1)
                .build();

        CategoryDto savedCategory = categoryService.save(expectedCategory);

        assertNotNull(savedCategory);
        assertNotNull(savedCategory.getId());
        assertEquals(expectedCategory.getCode(), savedCategory.getCode());
        assertEquals(expectedCategory.getDesignation(), savedCategory.getDesignation());
        //assertEquals(expectedCategory.getIdEntreprise(), savedCategory.getIdEntreprise());
    }

    @Test
    public void shouldUpdateCategoryWithSuccess() {
        CategoryDto expectedCategory = CategoryDto.builder()
                .code("Cat test")
                .designation("Designation test")
                //.idEntreprise(1)
                .build();

        CategoryDto savedCategory = categoryService.save(expectedCategory);

        CategoryDto categoryToUpdate = savedCategory;
        categoryToUpdate.setCode("Cat Update");

        savedCategory = categoryService.save(categoryToUpdate);

        assertNotNull(categoryToUpdate);
        assertNotNull(categoryToUpdate.getId());
        assertEquals(categoryToUpdate.getCode(), savedCategory.getCode());
        assertEquals(categoryToUpdate.getDesignation(), savedCategory.getDesignation());
        //assertEquals(categoryToUpdate.getIdEntreprise(), savedCategory.getIdEntreprise());
    }

    @Test
    public void shouldThrowInvalidEntityException() {
        CategoryDto expectedCategory = CategoryDto.builder().build();

        InvalidEntityException exception = assertThrows(InvalidEntityException.class, () -> categoryService.save(expectedCategory));

        assertEquals(ErrorCodes.CATEGORY_NOT_VALID, exception.getErrorCodes());
        assertEquals(1, exception.getErrors().size());
        assertEquals("Veuillez entrer le code de la categorie", exception.getErrors().get(0));
    }

    @Test
    public void shouldThrowEntityNotFoundException() {
        long nonExistentId = 0;

        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class, () -> {
            categoryService.findById(0);
        });

        assertEquals(ErrorCodes.CATEGORY_NOT_FOUND, exception.getErrorCodes());
        assertEquals("Aucune categorie a été trouvé avec l'ID = " + nonExistentId + " dans la base de donnée", exception.getMessage());
    }
}
