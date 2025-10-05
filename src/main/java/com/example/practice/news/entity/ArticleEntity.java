package com.example.practice.news.entity;

import com.example.practice.news.dto.ArticleDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name="article")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ArticleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "source", foreignKey = @ForeignKey(name = "article_ibfk_1"))
    private SourceEntity source;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category", foreignKey = @ForeignKey(name = "article_ibfk_2"))
    private CategoryEntity category;

    @Column(length = 150)
    private String author;

    @Column(length = 500)
    private String title;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(length = 500)
    private String url;

    @Column(name = "url_to_image", length = 1000)
    private String urlToImage;

    @Column(length = 100)
    private String publishedAt;

    @Column(columnDefinition = "TEXT")
    private String content;

    // db에서 값을 자동으로 생성해주는 컬럼들이므로 jpa가 필드에 값을 직접 세팅하지 않게 막는 내용.
    @Column(name = "created_at", updatable = false,insertable = false)
    private LocalDateTime createdAt;

    // db에서 값을 자동으로 생성해주는 컬럼들이므로 jpa가 필드에 값을 직접 세팅하지 않게 막는 내용.
    @Column(name = "updated_at", insertable = false)
    private LocalDateTime updatedAt;


    public static ArticleDTO toArticleEntity(ArticleDTO articleDTO){
        return ArticleDTO.builder()
                .source(articleDTO.getSource())
                .category(articleDTO.getCategory())
                .author(articleDTO.getAuthor())
                .title(articleDTO.getTitle())
                .description(articleDTO.getDescription())
                .url(articleDTO.getUrl())
                .urlToImage(articleDTO.getUrlToImage())
                .publishedAt(articleDTO.getPublishedAt())
                .content(articleDTO.getContent())
                .build();
    }
}
