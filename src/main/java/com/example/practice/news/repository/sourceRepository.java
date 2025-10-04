package com.example.practice.news.repository;

import com.example.practice.news.entity.sourceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface sourceRepository extends JpaRepository<sourceEntity, Long> {
}
