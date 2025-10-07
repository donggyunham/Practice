package com.example.practice.news.dto.funcdto;

import com.example.practice.news.dto.ArticleDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ArticleResponse {
    private String status;
    private Long totalResults;
    private ArticleDTO[] articles;
}
