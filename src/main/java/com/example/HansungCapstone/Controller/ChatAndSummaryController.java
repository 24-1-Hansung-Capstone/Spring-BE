package com.example.HansungCapstone.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.HansungCapstone.Service.Flask.ChatAndSummaryService;

@RestController
public class ChatAndSummaryController {

    @Autowired
    private final ChatAndSummaryService chatAndSummaryService;

    public ChatAndSummaryController(ChatAndSummaryService chatAndSummaryService) {
        this.chatAndSummaryService = chatAndSummaryService;
    }

    @PostMapping("/chat")
    public String sendChatRequest(@RequestParam String question) {
        return chatAndSummaryService.sendChatRequest(question);
    }
}
