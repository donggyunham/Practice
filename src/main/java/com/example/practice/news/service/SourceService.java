package com.example.practice.news.service;

import com.example.practice.news.dto.SourceDTO;
import com.example.practice.news.dto.funcdto.SourceResponse;
import com.example.practice.news.entity.SourceEntity;
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

@Service
@RequiredArgsConstructor
public class SourceService {

    @Value("${newsapi.source_url}")
    private String sourceURL;

    @Value("${newsapi.apikey}")
    private String apiKey;

    private final SourceRepository sourceRepository;

    public List<SourceDTO> getSource(){
        List<SourceEntity> sources = sourceRepository.findAll();
        return sources.stream().map(SourceEntity::toSourceDTO).toList();
    }

    @Transactional
    public void inputSourceData() throws URISyntaxException, IOException, InterruptedException {
        String url = sourceURL + apiKey;
        System.out.println(url);

        HttpClient client = HttpClient.newBuilder().build();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(url))
                .GET()
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        String resBody = response.body();

        Gson gson = new Gson();

        SourceResponse sourceResponse = gson.fromJson(resBody, SourceResponse.class);

        // 상태 및 데이터 확인
        System.out.println("Response status: " + sourceResponse.getStatus());
        /*System.out.println("값이 들어왔나?" + Arrays.toString(sourceResponse.getSources()));*/
        System.out.println("Source count: " + sourceResponse.getSources().length);

        try{
            for (SourceDTO sourceDTO : sourceResponse.getSources()){
                SourceEntity sourceEntity = SourceEntity.toSourceEntity(sourceDTO);
                sourceRepository.save(sourceEntity);
            }
        }catch(Exception e){
            throw new RuntimeException(e);
        }
    }
}
