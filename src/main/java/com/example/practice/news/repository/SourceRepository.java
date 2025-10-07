package com.example.practice.news.repository;

import com.example.practice.news.entity.SourceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SourceRepository extends JpaRepository<SourceEntity, Long> {
    Optional<SourceEntity> findByName(String name);
}
