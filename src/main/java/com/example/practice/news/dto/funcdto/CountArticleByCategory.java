package com.example.practice.news.dto.funcdto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CountArticleByCategory {
    private String category;
    private Long count;
}
