package com.example.practice.news.entity;

import com.example.practice.news.dto.SourceDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name="source")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SourceEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100)
    private Long sid;

    @Column(length = 100)
    private String name;

    @Column(length = 1000)
    private String description;

    @Column(length = 50)
    private String url;

    @Column(length = 50)
    private String category;

    @Column(length = 50)
    private String language;

    @Column(length = 10)
    private String country;

    @Column(name = "created_at", updatable = false, insertable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", insertable = false)
    private LocalDateTime updatedAt;

    public static SourceDTO tosourceEntity(SourceDTO sourceDTO){
        return SourceDTO.builder()
                .sid(sourceDTO.getSid())
                .name(sourceDTO.getName())
                .description(sourceDTO.getDescription())
                .url(sourceDTO.getUrl())
                .category(sourceDTO.getCategory())
                .language(sourceDTO.getLanguage())
                .country(sourceDTO.getCountry())
                .build();
    }
}
