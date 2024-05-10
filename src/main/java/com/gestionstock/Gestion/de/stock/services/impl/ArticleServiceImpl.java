package com.gestionstock.Gestion.de.stock.services.impl;

import com.gestionstock.Gestion.de.stock.dto.ArticleDto;
import com.gestionstock.Gestion.de.stock.exception.EntityNotFoundException;
import com.gestionstock.Gestion.de.stock.exception.ErrorCodes;
import com.gestionstock.Gestion.de.stock.exception.InvalidEntityException;
import com.gestionstock.Gestion.de.stock.model.Article;
import com.gestionstock.Gestion.de.stock.repository.ArticleRepository;
import com.gestionstock.Gestion.de.stock.services.ArticleService;
import com.gestionstock.Gestion.de.stock.validator.ArticleValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ArticleServiceImpl implements ArticleService {

    private ArticleRepository articleRepository;

    @Autowired
    public ArticleServiceImpl(ArticleRepository articleRepository) {

        this.articleRepository = articleRepository;
    }

    @Override
    public ArticleDto save(ArticleDto articleDto) {

        //verifie si l'article est valide
        List<String> errors = ArticleValidator.validate(articleDto);
        if (!errors.isEmpty()){
            log.error("Article non valide {}", articleDto);
            throw new InvalidEntityException("l'article n'est pas valide" , ErrorCodes.ARTICLE_NOT_VALID, errors);
        }
        return ArticleDto.fromEntity(articleRepository.save(ArticleDto.toEntity(articleDto)));
    }

    @Override
    public ArticleDto findById(Integer id) {
        if (id == null){
            log.error("L'ID de l'article  est null");
            return null;
        }

        Optional<Article> article = articleRepository.findById(id);

        ArticleDto articleDto = ArticleDto.fromEntity(article.get());

        return Optional.of(articleDto).orElseThrow(() -> new EntityNotFoundException(
                "Aucun article a été trouvé avec l 'ID = " + id + "dans la base de donnée",
                ErrorCodes.ARTICLE_NOT_FOUND)
        );
    }

    @Override
    public ArticleDto findByCodeArticle(String codeArticle) {
        // verifie le code de l'article
        if (!StringUtils.hasLength(codeArticle)){
            log.error("Le CODE de l'article  est null");
            return null;
        }
        Optional<Article> article = articleRepository.findArticleAByCodeArticle(codeArticle);

        ArticleDto articleDto = ArticleDto.fromEntity(article.get());

        return Optional.of(articleDto).orElseThrow(() -> new EntityNotFoundException(
                "Aucun article n'a été trouvé avec le code = " + codeArticle + "dans la base de donnée",
                ErrorCodes.ARTICLE_NOT_FOUND)
        );
    }

    @Override
    public List<ArticleDto> findAll() {
        return articleRepository.findAll().stream()
            .map(ArticleDto::fromEntity)
            .collect(Collectors.toList());

    }

    @Override
    public void delete(Integer id) {
        if (id == null){
            log.error("L'ID de l'article  est null");
            return ;
        }
        articleRepository.deleteById(id);

    }
}
