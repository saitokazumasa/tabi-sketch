package com.tabisketch.bean.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.ArrayList;

@Getter
@AllArgsConstructor
public class GeminiResponse {

    public record Part(String text) {
    }

    public record Content(String role, ArrayList<Part> parts) {
    }

    public record Candidate(Content content, String finishReason, float avgLogprobs) {
    }

    public record PromptTokensDetail(String modality, int tokenCount) {
    }

    public record CandidatesTokensDetail(String modality, int tokenCount) {
    }

    public record UsageMetadata(
            int promptTokenCount,
            int candidatesTokenCount,
            int totalTokenCount,
            ArrayList<PromptTokensDetail> promptTokensDetails,
            ArrayList<CandidatesTokensDetail> candidatesTokensDetails
    ) {
    }

    private ArrayList<Candidate> candidates;
    private UsageMetadata usageMetadata;
    private String modelVersion;
}
