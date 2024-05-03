package com.example.HansungCapstone.Service.Flask;

import com.example.HansungCapstone.Repository.Flask.ChatAndSummaryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChatAndSummaryService {

    @Autowired
    private final ChatAndSummaryRepository chatAndSummaryRepository;

    public ChatAndSummaryService(ChatAndSummaryRepository chatAndSummaryRepository) {
        this.chatAndSummaryRepository = chatAndSummaryRepository;
    }

    public String sendChatRequest(String question) {
        return chatAndSummaryRepository.sendChatRequest(question);
    }

    public String resultSummrayRequest() {
        return chatAndSummaryRepository.sendSummaryRequest();
    }
}
