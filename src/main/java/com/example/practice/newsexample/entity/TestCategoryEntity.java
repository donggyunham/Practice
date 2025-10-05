package com.example.practice.newsexample.entity;

import com.example.practice.news.dto.CategoryDTO;
import com.example.practice.news.entity.CategoryEntity;
import com.example.practice.newsexample.dto.TestCategoryDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name="category")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TestCategoryEntity {
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

    public static TestCategoryDTO toTestCategoryDTO(TestCategoryEntity testCategoryEntity){
        return TestCategoryDTO.builder()
                .name(testCategoryEntity.getName())
                .memo(testCategoryEntity.getMemo())
                .build();
    }

    public static TestCategoryEntity toTestCategoryEntity(TestCategoryDTO testCategoryDTO){
        TestCategoryEntity testCategoryEntity = new TestCategoryEntity();
        testCategoryEntity.name = testCategoryDTO.getName();
        testCategoryEntity.memo = testCategoryDTO.getMemo();
        return testCategoryEntity;
    }
}
