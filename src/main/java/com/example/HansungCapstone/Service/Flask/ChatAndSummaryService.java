package com.example.HansungCapstone.Service.Flask;

import com.example.HansungCapstone.DTO.Es.EsDtoWrapper;
import com.example.HansungCapstone.Repository.Flask.ChatAndSummaryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChatAndSummaryService {

    @Autowired
    private final ChatAndSummaryRepository chatAndSummaryRepository;

    public ChatAndSummaryService(ChatAndSummaryRepository chatAndSummaryRepository) {
        this.chatAndSummaryRepository = chatAndSummaryRepository;
    }

    public String getSentimental(String target) {
        return chatAndSummaryRepository.sendSentimentalRequest(target);
    }

    public String resultSummrayRequest(List<EsDtoWrapper> searchResult) {
        return chatAndSummaryRepository.sendSummaryRequest(searchResult);
    }
}
