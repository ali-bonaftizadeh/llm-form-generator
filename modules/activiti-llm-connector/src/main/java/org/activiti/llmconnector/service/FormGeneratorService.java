package org.activiti.llmconnector.service;

import org.activiti.form.model.FormDefinition;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;


@Service
public class FormGeneratorService {

    private final ChatClient chatClient;

    public FormGeneratorService(ChatClient chatClient) {
        this.chatClient = chatClient;
    }

    public FormDefinition generateFormFromPrompt(String prompt) {
        return chatClient.prompt()
                .user(prompt)
                .call()
                .entity(FormDefinition.class);
    }
}
