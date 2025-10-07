package com.example.practice.news.entity;

import com.example.practice.news.dto.CategoryDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name="category")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CategoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 50, nullable = false)
    private String name;

    @Column(length = 500)
    private String memo;

    @Column(name = "created_at", updatable = false, insertable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", insertable = false)
    private LocalDateTime updatedAt;

    public static CategoryDTO toCategoryDTO(CategoryEntity categoryEntity){
        return CategoryDTO.builder()
                .name(categoryEntity.getName())
                .memo(categoryEntity.getMemo())
                .build();
    }

    public static CategoryEntity toCategoryEntity(CategoryDTO categoryDTO){
        CategoryEntity categoryEntity = new CategoryEntity();
        categoryEntity.name = categoryDTO.getName();
        categoryEntity.memo = categoryDTO.getMemo();
        return categoryEntity;
    }

    public static CategoryEntity queryByName(String name){
        CategoryEntity entity = new CategoryEntity();
        entity.name = name;
        return entity;
    }
}
