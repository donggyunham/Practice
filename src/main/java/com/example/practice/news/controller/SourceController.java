package com.example.practice.news.controller;

import com.example.practice.news.dto.SourceDTO;
import com.example.practice.news.service.SourceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;


@Controller
@RequiredArgsConstructor
public class SourceController {

    private final SourceService sourceService;

    @GetMapping("/source")
    public String getSource(Model model){
        List<SourceDTO> sources = sourceService.getSource();
        model.addAttribute("sources",sources);
        return "/menu/source";
    }

    @PostMapping("/inputSource")
    public String inputSource(Model model){
        try{
            sourceService.inputSourceData();;
        }catch (URISyntaxException | IOException | InterruptedException e){
            e.getStackTrace();
            model.addAttribute("ERROR", e.getMessage());
            return "/menu/source";
        }
        return "/menu/source";
    }
}
