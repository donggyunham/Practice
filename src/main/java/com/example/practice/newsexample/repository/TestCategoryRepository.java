package com.example.practice.newsexample.repository;

import com.example.practice.newsexample.entity.TestCategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TestCategoryRepository extends JpaRepository<TestCategoryEntity, Long> {
}
