package org.activiti.llmconnector.config;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;

@Configuration
public class AIChatClientConfig {

    private static final String FORM_DSL_FILE_PATH = "/form_dsl.txt";

    @Bean
    public ChatClient chatClient(OpenAiChatModel model) {
        String dsl = readDSLFromFile();
        return ChatClient.builder(model).defaultSystem(dsl).build();
    }

    private String readDSLFromFile() {
        try (InputStream inputStream = getClass().getResourceAsStream(FORM_DSL_FILE_PATH)) {
            if (inputStream == null) {
                throw new IllegalArgumentException("Resource not found: " + FORM_DSL_FILE_PATH);
            }

            try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))) {
                return reader.lines().collect(Collectors.joining("\n"));
            }
        } catch (IOException e) {
            throw new RuntimeException("Failed to load resource: " + FORM_DSL_FILE_PATH, e);
        }
    }
}
