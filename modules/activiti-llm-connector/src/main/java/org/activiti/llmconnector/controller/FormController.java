package org.activiti.llmconnector.controller;

import org.activiti.llmconnector.service.FormGeneratorService;
import org.activiti.form.model.FormDefinition;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/form/")
public class FormController {
    private final FormGeneratorService service;

    public FormController(FormGeneratorService service) {
        this.service = service;
    }

    @GetMapping("/generate-from-prompt")
    public ResponseEntity<FormDefinition> generateFromPrompt(@RequestParam String prompt) {
        return ResponseEntity.ok(service.generateFormFromPrompt(prompt));
    }
}
