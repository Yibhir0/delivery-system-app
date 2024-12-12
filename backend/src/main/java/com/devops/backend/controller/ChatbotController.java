package com.devops.backend.controller;

import com.devops.backend.service.ChatbotService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/api/chatbot", produces = "application/json")
public class ChatbotController {

    private final ChatbotService chatbotService;

    @Autowired
    public ChatbotController(ChatbotService chatbotService) {
        this.chatbotService = chatbotService;
    }

    @Tag(name = "User POST Endpoints")
    @Operation(summary = "Ask chatbot a question")
    @PostMapping(value="/ask", consumes = "application/json")
    public ResponseEntity<String> askChatbot(@RequestBody String question) {
        String response = chatbotService.askChatbot(question);
        return ResponseEntity.ok(response);
    }
}
