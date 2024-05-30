package com.example.HansungCapstone.Controller;

import com.example.HansungCapstone.DTO.Es.EsDto;
import com.example.HansungCapstone.DTO.Es.EsDtoWrapper;
import com.example.HansungCapstone.Service.Es.EsSatisfactionService;
import com.example.HansungCapstone.Service.Es.TypoCurrectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;

@RestController
@CrossOrigin(origins = {
        "http://localhost:3000", // 로컬 개발 환경
        "http://13.125.234.8:3000" // 배포환경
})
public class SatisfactionController {
    @Autowired
    private EsSatisfactionService esSatisfactionService;

    @Autowired
    private TypoCurrectionService typoCurrectionService;

    @GetMapping("/satisfaction")
    public ResponseEntity<List<EsDto>> search(@RequestParam String query) throws IOException {
        boolean isQueryChanged = false;

        // 오탈차 전처리
        String newQuery = typoCurrectionService.correctTypo(query);

        // 오탈자 수정으로 인해 변경이 발생하면, 해당 사항을 프론트에 전달
        isQueryChanged = !newQuery.isEmpty();

        // 검색
        List<EsDto> searchResult = esSatisfactionService.search(query);

        HttpHeaders headers = new HttpHeaders();
        headers.add("isQueryChanged", String.valueOf(isQueryChanged));
        if (isQueryChanged) {
            headers.add("suggestQuery", URLEncoder.encode(newQuery, StandardCharsets.UTF_8));
        }

        return new ResponseEntity<>(searchResult, headers, HttpStatus.OK);
    }
}
