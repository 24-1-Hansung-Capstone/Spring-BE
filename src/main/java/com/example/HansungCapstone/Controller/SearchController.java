package com.example.HansungCapstone.Controller;

import com.example.HansungCapstone.DTO.Es.EsDtoWrapper;
import com.example.HansungCapstone.Service.EsSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = {"http://localhost:3000",}) // TODO : cors 설정 필요할 수 있음
public class SearchController {

    @Autowired
    private EsSearchService esSearchService;
    @GetMapping("/search")
    public List<EsDtoWrapper> searchTest(@RequestParam String query) throws IOException {
        return esSearchService.search(query);

    }
}
