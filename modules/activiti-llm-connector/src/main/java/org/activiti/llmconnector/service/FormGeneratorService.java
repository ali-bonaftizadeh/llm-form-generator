package org.activiti.llmconnector.service;

import org.activiti.form.model.FormDefinition;
import org.activiti.form.model.FormField;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

import java.util.List;


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

//        FormDefinition formDefinition = new FormDefinition();
//        formDefinition.setName("new form");
//        formDefinition.setId("your opinion");
//        FormField e1 = new FormField();
//        e1.setType("text");
//        formDefinition.setFields(List.of(e1));
//        return formDefinition;
    }
}
