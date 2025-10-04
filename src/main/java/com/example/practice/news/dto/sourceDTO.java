package com.example.practice.news.dto;

import lombok.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class sourceDTO {
    private Long id;
    private Long sid;
    private String name;
    private String description;
    private String url;
    private String category;
    private String language;
    private String country;
    private String createdAt;
    private String updatedAt;
}
