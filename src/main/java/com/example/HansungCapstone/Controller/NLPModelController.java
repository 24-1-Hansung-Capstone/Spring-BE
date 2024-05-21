package com.example.HansungCapstone.Controller;

import com.example.HansungCapstone.DTO.Es.EsDto;
import com.example.HansungCapstone.DTO.Es.EsDtoWrapper;
import com.example.HansungCapstone.DTO.Es.Impl.EsNewsDto;
import com.example.HansungCapstone.DTO.UrlRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.HansungCapstone.Service.Flask.ChatAndSummaryService;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/nlpModel")
@CrossOrigin(origins = {
        "http://localhost:3000", // 로컬 개발 환경
        "http://13.125.234.8:3000" // 배포환경
})
public class NLPModelController {

    @Autowired
    private final ChatAndSummaryService chatAndSummaryService;

    @Autowired
    private SearchController searchController;

    public NLPModelController(ChatAndSummaryService chatAndSummaryService) {
        this.chatAndSummaryService = chatAndSummaryService;
    }


    @PostMapping("/summary")
    public List<String> docSummary(@RequestBody UrlRequest urlRequest) {
        var searchResults = searchController.getSearchResult();
        String searchResult = null, searchTitle = null;
        String url = urlRequest.getUrl();

        for (EsDtoWrapper esDto : searchResults) {
            EsNewsDto esNewsDto;
            try {
                esNewsDto = (EsNewsDto) esDto.getEsDto();
            }
            catch (ClassCastException cce) {
                continue;
            }
            if (esNewsDto.getUrl().equals(url)) {
                searchResult = esNewsDto.getMainBody();
                searchTitle = esNewsDto.getTitle();
                break;
            }
        }
        if (searchResult == null) {
            return new ArrayList<>();
        }
        else {
            return chatAndSummaryService.resultSummrayRequest(searchTitle, searchResult);
        }
    }

    @PostMapping("/sentimental")
    public String measureSentimental(@RequestBody String target) {
        return chatAndSummaryService.getSentimental(target);
    }
}
