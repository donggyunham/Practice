package com.example.practice.newsexample.dto;

import lombok.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class TestCategoryDTO {
    private Long id;
    private String name;
    private String memo;
    private String createdAt;
    private String updatedAt;
}
