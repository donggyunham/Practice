package com.example.practice.news.repository;

import com.example.practice.news.entity.articleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface articleRepository extends JpaRepository<articleEntity, Long> {
}
