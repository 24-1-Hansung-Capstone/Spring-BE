package com.example.HansungCapstone.Service.Flask;

import com.example.HansungCapstone.DTO.Es.EsDtoWrapper;
import com.example.HansungCapstone.DTO.Es.Impl.EsNewsDto;
import com.example.HansungCapstone.Repository.Flask.ChatAndSummaryRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ChatAndSummaryService {
    private int summaryResponse = 2;

    @Autowired
    private final ChatAndSummaryRepository chatAndSummaryRepository;

    public ChatAndSummaryService(ChatAndSummaryRepository chatAndSummaryRepository) {
        this.chatAndSummaryRepository = chatAndSummaryRepository;
    }

    public String getSentimental(String target) {
        return chatAndSummaryRepository.sendSentimentalRequest(target);
    }

    public List<String> resultSummrayRequest(String searchTitle, String searchResult) {
        String responseJson = chatAndSummaryRepository.sendSummaryRequest(searchTitle, searchResult);
        List<String> result = new ArrayList<>();
        result.add(responseJson);
        return result;
    }
}
