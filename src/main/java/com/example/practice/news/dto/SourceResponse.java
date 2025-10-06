package com.example.practice.news.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class SourceResponse {
    private String status;
    private SourceDTO[] sources;
}
