package com.tabisketch.repository.implement;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tabisketch.bean.entity.GeminiRequest;
import com.tabisketch.bean.entity.GeminiResponse;
import com.tabisketch.exception.NullResponseException;
import com.tabisketch.repository.IGeminiRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class GeminiRepository implements IGeminiRepository {
    private final String url;
    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    public GeminiRepository(
            final @Value("${GEMINI_API_URL}") String geminiAPIUrl,
            final @Value("${GEMINI_API_KEY}") String geminiAPIKey,
            final RestTemplateBuilder restTemplateBuilder,
            final ObjectMapper objectMapper
    ) {
        this.url = geminiAPIUrl + "?key=" + geminiAPIKey;
        this.restTemplate = restTemplateBuilder.build();
        this.objectMapper = objectMapper;
    }

    @Override
    public String request(final String text) throws JsonProcessingException, NullResponseException {
        // Headersを作成
        final var headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        // Bodyを作成
        final var body = new GeminiRequest(text);
        final var json = this.objectMapper.writeValueAsString(body);

        // リクエストしてResponseを取得
        final var request = new HttpEntity<>(json, headers);
        final var response = restTemplate.postForEntity(url, request, GeminiResponse.class);

        // nullチェック
        if (response.getBody() == null) throw new NullResponseException("response is null");

        final String responseText = response
                .getBody()
                .getCandidates().getFirst()
                .content()
                .parts().getFirst()
                .text();

        System.out.println(responseText);
        return responseText;
    }
}
