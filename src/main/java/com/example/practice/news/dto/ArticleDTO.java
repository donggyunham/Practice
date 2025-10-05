package com.example.practice.news.dto;

import lombok.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class ArticleDTO {
    private Long id;
    private Long source;
    private String category;
    private String author;
    private String title;
    private String description;
    private String url;
    private String urlToImage;
    private String publishedAt;
    private String content;
    private String createdAt;
    private String updatedAt;
}
