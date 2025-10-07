package com.example.practice.news.repository;

import com.example.practice.news.dto.funcdto.CountArticleByCategory;
import com.example.practice.news.entity.ArticleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ArticleRepository extends JpaRepository<ArticleEntity, Long> {

    Optional<ArticleEntity> findByUrl(String url);

    @Query("select new com.example.practice.news.dto.funcdto.CountArticleByCategory(a.category.name, COUNT(a.id)) " +
            "from ArticleEntity a " +
            "group by a.category.name " +
            "order by COUNT(a.id) desc"
    )
    List<CountArticleByCategory> countArticleByCategory();
}
