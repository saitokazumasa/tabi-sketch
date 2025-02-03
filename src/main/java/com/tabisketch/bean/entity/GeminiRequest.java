package com.tabisketch.bean.entity;

import lombok.Getter;

import java.util.ArrayList;

@Getter
public class GeminiRequest {

    public record Part(String text) {
    }

    @Getter
    public static class Content {
        private final String role;
        private final ArrayList<Part> parts;

        public Content(final Part part) {
            role = "user";
            parts = new ArrayList<>();
            parts.add(part);
        }
    }

    @Getter
    private static class GenerationConfig {
        private final float temperature;
        private final float topK;
        private final float topP;
        private final int maxOutputTokens;
        private final String responseMimeType;
//        private final String responseSchema;

        public GenerationConfig() {
            this.temperature = 0;
            this.topK = 40;
            this.topP = 0.95f;
            this.maxOutputTokens = 8192;
            this.responseMimeType = "text/plain";
//            this.responseSchema = "";
        }
    }

    private final ArrayList<Content> contents;
    private final GenerationConfig generationConfig;

    public GeminiRequest(final String text) {
        final var part = new Part(text);
        final var content = new Content(part);
        this.contents = new ArrayList<>();
        contents.add(content);

        this.generationConfig = new GenerationConfig();
    }
}
