package com.example.HansungCapstone.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.HansungCapstone.Service.Flask.ChatAndSummaryService;

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
    public List<String> docSummary() {
        return chatAndSummaryService.resultSummrayRequest(searchController.getSearchResult());
    }

    @PostMapping("/sentimental")
    public String measureSentimental(@RequestBody String target) {
        return chatAndSummaryService.getSentimental(target);
    }
}
