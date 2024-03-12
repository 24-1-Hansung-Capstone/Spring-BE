package com.example.HansungCapstone;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@CrossOrigin(origins = {"http://localhost:3000",}) // TODO : cors 설정 필요할 수 있음
public class SearchController {

    @GetMapping("/search")
    public ArrayList<SearchResult> search(@RequestParam("query") String query) {
        // TODO : 반환은 json으로 하도록 변경해야함

        ArrayList<SearchResult> searchResults = new ArrayList<>();
        for(int i = 0; i<500; i++){
            searchResults.add(new SearchResult(query+i, query+i, query+i, query+i));
        }

        return searchResults;
    }

    static class SearchResult{
        public String url;
        public String title;
        public String category;
        public String preview;

        public SearchResult(String url, String title, String category, String preview) {
            this.url = url;
            this.title = title;
            this.category = category;
            this.preview = preview;
        }
    }
}
