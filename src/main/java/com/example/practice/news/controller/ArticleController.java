package com.example.practice.news.controller;

import com.example.practice.news.dto.CategoryDTO;
import com.example.practice.news.dto.funcdto.CountArticleByCategory;
import com.example.practice.news.service.ArticleService;
import com.example.practice.news.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class ArticleController {

    private final ArticleService articleService;
    private final CategoryService categoryService;

    @GetMapping("/article")
    public String getArticle(Model model) {
        List<CategoryDTO> categoryDTOS = categoryService.getCategory();
        Long articleCount = articleService.getTotalArticleCount();
        List<CountArticleByCategory> countArticleByCategories = articleService.countByArticleByCategories();

        model.addAttribute("articleCount", articleCount);
        model.addAttribute("countsByCategory", countArticleByCategories);
        model.addAttribute("categories",categoryDTOS);

        return "/menu/article";
    }
    @PostMapping("/inputArticle")
    public String inputArticle(@RequestParam("categoryName") String category, Model model){
        try{
            articleService.inputArticleService(category);
        } catch (URISyntaxException | IOException | InterruptedException e) {
            e.getStackTrace();
            model.addAttribute("ERROR", e.getMessage());
            return "article";
        }
        return "redirect:/article";
    }
}
