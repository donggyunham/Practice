package com.example.practice.news.dto.funcdto;

import com.example.practice.news.dto.SourceDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class SourceResponse {
    private String status;
    private SourceDTO[] sources;
}
