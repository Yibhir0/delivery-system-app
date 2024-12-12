package com.devops.backend.service;

import com.devops.backend.service.singleton.Chatbot;
import org.springframework.stereotype.Service;

@Service
public class ChatbotService {

    public String askChatbot(String message) {
        return Chatbot.getInstance().getResponse(message);
    }
}
