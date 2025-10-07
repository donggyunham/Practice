package com.example.practice.news.service;

import com.example.practice.news.dto.ArticleDTO;
import com.example.practice.news.dto.funcdto.ArticleResponse;
import com.example.practice.news.dto.funcdto.CountArticleByCategory;
import com.example.practice.news.entity.ArticleEntity;
import com.example.practice.news.entity.CategoryEntity;
import com.example.practice.news.entity.SourceEntity;
import com.example.practice.news.repository.ArticleRepository;
import com.example.practice.news.repository.CategoryRepository;
import com.example.practice.news.repository.SourceRepository;
import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ArticleService {

    @Value("${newsapi.article_url}")
    private String articleUrl;

    @Value("${newsapi.apikey}")
    private String apiKey;

    private final ArticleRepository articleRepository;
    private final SourceRepository sourceRepository;
    private final CategoryRepository categoryRepository;

    @Transactional
    public Long getTotalArticleCount() {return articleRepository.count();}

    public List<CountArticleByCategory> countByArticleByCategories () {
        return articleRepository.countArticleByCategory();
    }

    @Transactional
    public void inputArticleService(String category) throws URISyntaxException, IOException, InterruptedException, RuntimeException {
        String url = String.format("%scategory=%s&%s", articleUrl, category, apiKey);
        System.out.println(url);
        if(articleUrl == null || category == null || apiKey == null) {
            throw new RuntimeException("URL 구성에 필요한 값이 null입니다: articleUrl=" + articleUrl + ", category=" + category + ", apiKey=" + apiKey);
        }
        HttpClient client = HttpClient.newBuilder().build();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(url))
                .GET()
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        String resBody = response.body();

        Gson gson = new Gson();

        ArticleResponse articleResponse = gson.fromJson(resBody, ArticleResponse.class);

        System.out.println(articleResponse.getStatus());
        System.out.println(articleResponse.getTotalResults());
        System.out.println(articleResponse.getArticles()[0].getAuthor());

        saveArticleService(articleResponse, category);
    }

    public void saveArticleService(ArticleResponse articleResponse, String category){
        try {
            for (ArticleDTO articleDTO : articleResponse.getArticles()){
                if(articleDTO.getUrl() != null){
                    boolean exists = articleRepository.findByUrl(articleDTO.getUrl()).isPresent();
                    if(exists) continue;
                }
                Optional<SourceEntity> srcOpt = sourceRepository.findByName(articleDTO.getSource().getName());

                SourceEntity src = srcOpt.orElseGet(() -> {
                    SourceEntity s1 = SourceEntity.queryByName(articleDTO.getSource().getName());
                    return sourceRepository.save(s1);
                });

                Optional<CategoryEntity> catOpt = categoryRepository.findByName(category);

                CategoryEntity cat = catOpt.orElseGet(() -> {
                    CategoryEntity c = CategoryEntity.queryByName(category);
                    return categoryRepository.save(c);
                });

                ArticleEntity articleEntity = ArticleEntity.toArticleEntity(articleDTO, src, cat);
                articleRepository.save(articleEntity);
            }
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }
}
