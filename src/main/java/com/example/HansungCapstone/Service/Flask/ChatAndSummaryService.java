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

    public List<String> resultSummrayRequest(List<EsDtoWrapper> searchResult) {
        List<EsDtoWrapper> newsDtos = new ArrayList<>();
        List<String> titles = new ArrayList<>();

        for (EsDtoWrapper esDtoWrapper : searchResult){
            if(esDtoWrapper.getEsDto().getCategory().equals("news")) {
                newsDtos.add(esDtoWrapper);
                titles.add(((EsNewsDto)esDtoWrapper.getEsDto()).getTitle());
            }
            if(newsDtos.size() == summaryResponse) break;
        }

        if(newsDtos.size() == 0){
            List<String> result = new ArrayList<>();
            result.add("뉴스 검색 결과가 없습니다");
            return result;
        }

        String responseJson = chatAndSummaryRepository.sendSummaryRequest(newsDtos);
        ObjectMapper mapper = new ObjectMapper();
        try{
            List<String> responses = mapper.readValue(responseJson, new TypeReference<List<String>>(){});
            List<String> result = new ArrayList<>();
            for(int i = 0; i< summaryResponse; i++ ){
                result.add(titles.get(i));
                result.add(responses.get(i));
            }
            return result;
        } catch (Exception e) {
            List<String> result = new ArrayList<>();
            result.add("요약 중 오류가 발생했습니다.");
            return result;
        }
    }
}
