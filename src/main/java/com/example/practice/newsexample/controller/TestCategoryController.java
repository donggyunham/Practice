package com.example.practice.newsexample.controller;

import com.example.practice.newsexample.dto.TestCategoryDTO;
import com.example.practice.newsexample.service.TestCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class TestCategoryController {
    private final TestCategoryService testcService;

    @GetMapping("/testCategory")
    public String TestCategory (Model model) {
        List<TestCategoryDTO> testCategories = testcService.getTestDtoList();
        model.addAttribute("category", testCategories);
        return "/menu/category";

    }
}
