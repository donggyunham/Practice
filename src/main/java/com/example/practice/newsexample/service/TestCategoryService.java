package com.example.practice.newsexample.service;

import com.example.practice.newsexample.dto.TestCategoryDTO;
import com.example.practice.newsexample.entity.TestCategoryEntity;
import com.example.practice.newsexample.repository.TestCategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TestCategoryService {
    private final TestCategoryRepository testCategoryRepository;

    public List<TestCategoryDTO> getTestDtoList () {
        List<TestCategoryEntity> testEntityList = testCategoryRepository.findAll();
        List<TestCategoryDTO> testDtoList = new ArrayList<>();

        for (TestCategoryEntity testCategoryEntity : testEntityList) {
            TestCategoryDTO dto = TestCategoryEntity.toTestCategoryDTO(testCategoryEntity);
            testDtoList.add(dto);
        }
        return testDtoList;
    }
}
