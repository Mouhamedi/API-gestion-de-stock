package com.gestionstock.Gestion.de.stock.controller;

import com.gestionstock.Gestion.de.stock.controller.api.ArticleControllerApi;
import com.gestionstock.Gestion.de.stock.dto.ArticleDto;
import com.gestionstock.Gestion.de.stock.services.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ArticleController implements ArticleControllerApi {

    //Field Injection
    private ArticleService articleService;

    //Construtor Injection
    @Autowired
    public ArticleController(
            ArticleService articleService
    ) {
        this.articleService = articleService;
    }

    @Override
    public ArticleDto save(ArticleDto articleDto) {

        return articleService.save(articleDto);
    }

    @Override
    public ArticleDto findById(Integer id) {

        return articleService.findById(id);
    }

    @Override
    public ArticleDto findByCodeArticle(String codeArticle) {

        return articleService.findByCodeArticle(codeArticle);
    }

    @Override
    public List<ArticleDto> findAll() {

        return articleService.findAll();
    }

    @Override
    public void delete(Integer id) {
        articleService.delete(id);

    }
}