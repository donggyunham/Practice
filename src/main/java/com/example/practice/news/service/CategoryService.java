package com.example.practice.news.service;

import com.example.practice.news.dto.CategoryDTO;
import com.example.practice.news.entity.CategoryEntity;
import com.example.practice.news.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public List<CategoryDTO> getCategory(){
        return categoryRepository.findAll()
                .stream()
                .map(CategoryEntity::toCategoryDTO)
                .collect(Collectors.toList());
    }

    public String inputCategory(CategoryDTO categoryDTO){
        if (categoryDTO != null) {
            try {
                    CategoryEntity categoryEntity = CategoryEntity.toCategoryEntity(categoryDTO);
                    categoryRepository.save(categoryEntity);
            }catch (Exception e) {
                System.out.println(e.getMessage());
                return String.format("ERROR: %s", e.getMessage());
            }
            return "SUCCESS";
        }
        return "ERROR: 카테고리 정보가 없음.";
    }
}
