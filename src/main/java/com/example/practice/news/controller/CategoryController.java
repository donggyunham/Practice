package com.example.practice.news.controller;

import com.example.practice.news.dto.CategoryDTO;
import com.example.practice.news.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping("/category")
    public String category(Model model){
        List<CategoryDTO> categories = categoryService.getCategory();
        model.addAttribute("category", categories);
        return "/menu/category";
    }
    @PostMapping("/inputCategory")
    public String inputCategory(@RequestParam("category_name") String categoryName,
                                Model model){
        try {
            // 1️ 입력값 검증
            if (categoryName == null || categoryName.trim().isEmpty()) {
                model.addAttribute("ERROR", "카테고리 이름을 입력해주세요.");
                model.addAttribute("category", categoryService.getCategory());
                return "category";
            }

            // 2️ DTO 생성 (setter 대신 Builder 사용)
            CategoryDTO categoryDTO = CategoryDTO.builder()
                    .name(categoryName.trim())
                    .build();

            // 3️ 서비스 호출
            String msg = categoryService.inputCategory(categoryDTO);

            // 4 서비스 결과 확인
            if (msg != null && msg.startsWith("ERROR")) {
                model.addAttribute("ERROR", msg);
                model.addAttribute("category", categoryService.getCategory());
                return "category";
            }

            // 5️ 성공 시 리다이렉트
            return "redirect:/category";

        } catch (Exception e) {
            model.addAttribute("ERROR", "오류가 발생했습니다: " + e.getMessage());
            model.addAttribute("category", categoryService.getCategory());
            return "category";
        }
    }
}
