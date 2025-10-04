package com.example.practice.news.repository;

import com.example.practice.news.entity.categoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface categoryRepository extends JpaRepository<categoryEntity, Long> {
}
