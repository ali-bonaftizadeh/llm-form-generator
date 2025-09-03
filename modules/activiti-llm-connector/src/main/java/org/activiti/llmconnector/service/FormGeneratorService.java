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

    public static FormDefinition buildForm() {
        FormDefinition formDefinition = new FormDefinition();
        formDefinition.setName("سنجش میزان رضایت شغلی کارکنان همراه با دریافت کد پرسنلی و نام");
        formDefinition.setKey("سنجش_میزان_رضایت_شغلی");
        formDefinition.setVersion(0);

        // Employee ID field
        FormField employeeId = new FormField();
        employeeId.setId("employeeId");
        employeeId.setName("کد پرسنلی");
        employeeId.setType("text");
        employeeId.setRequired(true);
        employeeId.setReadOnly(false);
        employeeId.setOverrideId(true);
        employeeId.setPlaceholder("لطفا کد پرسنلی را وارد کنید");
        employeeId.setLayout(null);
        employeeId.setSizeX(0);
        employeeId.setSizeY(0);

        // Employee Name field
        FormField employeeName = new FormField();
        employeeName.setId("employeeName");
        employeeName.setName("نام");
        employeeName.setType("text");
        employeeName.setRequired(true);
        employeeName.setReadOnly(false);
        employeeName.setOverrideId(true);
        employeeName.setPlaceholder("لطفا نام را وارد کنید");
        employeeName.setLayout(null);
        employeeName.setSizeX(0);
        employeeName.setSizeY(0);

        // Satisfaction Level field
        FormField satisfactionLevel = new FormField();
        satisfactionLevel.setId("satisfactionLevel");
        satisfactionLevel.setName("میزان رضایت");
        satisfactionLevel.setType("integer");
        satisfactionLevel.setRequired(false);
        satisfactionLevel.setReadOnly(false);
        satisfactionLevel.setOverrideId(false);
        satisfactionLevel.setPlaceholder(null);
        satisfactionLevel.setLayout(null);
        satisfactionLevel.setSizeX(0);
        satisfactionLevel.setSizeY(0);

        formDefinition.setFields(List.of(employeeId, employeeName, satisfactionLevel));

        return formDefinition;
    }

    public FormDefinition generateFormFromPrompt(String prompt) {
        return chatClient.prompt()
                .user(prompt)
                .call()
                .entity(FormDefinition.class);
    }
}
