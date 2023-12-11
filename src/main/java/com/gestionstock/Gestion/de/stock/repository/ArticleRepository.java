package com.gestionstock.Gestion.de.stock.repository;

import com.gestionstock.Gestion.de.stock.model.Article;
import org.springframework.data.jpa.repository.JpaRepository;
public interface ArticleRepository extends JpaRepository<Integer, Article> {

    //Article findByCodeArticle(String code);
}
