package org.activiti.llmconnector.controller;

import org.activiti.llmconnector.service.FormGeneratorService;
import org.activiti.form.model.FormDefinition;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/form/")
@CrossOrigin(origins = "http://localhost:9999")
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
