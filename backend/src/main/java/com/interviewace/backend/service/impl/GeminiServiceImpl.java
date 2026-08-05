package com.interviewace.backend.service.impl;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.interviewace.backend.service.GeminiService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class GeminiServiceImpl implements GeminiService {

    @Value("${gemini.api.key}")
    private String apiKey;

    private final RestTemplate restTemplate = new RestTemplate();

    @Override
    public String evaluateAnswer(String question, String answer) {

        try {

            String prompt = """
                You are an interview evaluator.

                Question:
                %s

                Candidate Answer:
                %s

                Evaluate the answer.

                Give:

                Score out of 10

                Strengths

                Weaknesses

                Suggestions
                """.formatted(question, answer);

            String url =
                    "https://generativelanguage.googleapis.com/v1beta/models/gemini-2.0-flash:generateContent?key="
                            + apiKey;

            JsonObject text = new JsonObject();
            text.addProperty("text", prompt);

            JsonArray parts = new JsonArray();
            parts.add(text);

            JsonObject content = new JsonObject();
            content.add("parts", parts);

            JsonArray contents = new JsonArray();
            contents.add(content);

            JsonObject request = new JsonObject();
            request.add("contents", contents);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            HttpEntity<String> entity =
                    new HttpEntity<>(request.toString(), headers);

            ResponseEntity<String> response =
                    restTemplate.exchange(
                            url,
                            HttpMethod.POST,
                            entity,
                            String.class);

            JsonObject json =
                    JsonParser.parseString(response.getBody()).getAsJsonObject();

            return json
                    .getAsJsonArray("candidates")
                    .get(0)
                    .getAsJsonObject()
                    .getAsJsonObject("content")
                    .getAsJsonArray("parts")
                    .get(0)
                    .getAsJsonObject()
                    .get("text")
                    .getAsString();

        } catch (Exception e) {

            e.printStackTrace();

            return """
                Score: 8/10

                Strengths:
                - Answer is relevant.
                - Demonstrates basic understanding.

                Weaknesses:
                - Needs more technical depth.

                Suggestions:
                - Add more examples and explain concepts in detail.

                (Fallback response because Gemini API is unavailable.)
                """;
        }
    }
}