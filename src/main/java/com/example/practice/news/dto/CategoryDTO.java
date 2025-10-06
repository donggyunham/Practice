package com.example.practice.news.dto;

import lombok.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CategoryDTO {
    private Long id;
    private String name;
    private String memo;
    private String createdAt;
    private String updatedAt;
}
