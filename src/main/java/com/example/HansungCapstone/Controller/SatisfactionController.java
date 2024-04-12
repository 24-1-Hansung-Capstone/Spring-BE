package com.example.HansungCapstone.Controller;

import com.example.HansungCapstone.DTO.Es.EsDto;
import com.example.HansungCapstone.Service.Es.EsSatisfactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
@CrossOrigin(origins = {"http://localhost:3000",}) // TODO : cors 설정 필요할 수 있음
public class SatisfactionController {
    @Autowired
    private EsSatisfactionService esSatisfactionService;

    @GetMapping("/satisfaction")
    public List<EsDto> search(@RequestParam String query) throws IOException {
        return esSatisfactionService.search(query);
    }
}
