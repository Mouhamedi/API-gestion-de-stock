package com.gestionstock.Gestion.de.stock.controller;

import com.gestionstock.Gestion.de.stock.controller.api.CategoryControllerApi;
import com.gestionstock.Gestion.de.stock.dto.CategoryDto;
import com.gestionstock.Gestion.de.stock.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CategoryController implements CategoryControllerApi {

   private CategoryService categoryService;

   @Autowired
   public  CategoryController(
           CategoryService categoryService
   ){
      this.categoryService = categoryService;
   }

   @Override
   public CategoryDto save(CategoryDto categoryDto){

      return categoryService.save(categoryDto);
   }

   @Override
   public CategoryDto findById(Integer id) {

      return categoryService.findById(id);
   }

   @Override
   public CategoryDto findByCode(String code){

      return categoryService.findByCode(code);
   }

   @Override
   public List<CategoryDto> findAll() {
      return categoryService.findAll();
   }

   @Override
   public void delete(Integer id) {
      categoryService.delete(id);
   }

}
