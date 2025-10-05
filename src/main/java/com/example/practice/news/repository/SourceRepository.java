package com.example.practice.news.repository;

import com.example.practice.news.entity.SourceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SourceRepository extends JpaRepository<SourceEntity, Long> {
}
