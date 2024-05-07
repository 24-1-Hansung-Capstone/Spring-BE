package com.example.HansungCapstone.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.HansungCapstone.Service.Flask.ChatAndSummaryService;

@RestController
@RequestMapping("/nlpModel")
@CrossOrigin(origins = {"http://localhost:3000", })
public class NLPModelController {

    @Autowired
    private final ChatAndSummaryService chatAndSummaryService;

    @Autowired
    private SearchController searchController;

    public NLPModelController(ChatAndSummaryService chatAndSummaryService) {
        this.chatAndSummaryService = chatAndSummaryService;
    }


    @PostMapping("/summary")
    public String docSummary() {
        return chatAndSummaryService.resultSummrayRequest(searchController.getSearchResult());
    }

    @PostMapping("/sentimental")
    public String measureSentimental(@RequestBody String target) {
        return chatAndSummaryService.getSentimental(target);
    }
}
