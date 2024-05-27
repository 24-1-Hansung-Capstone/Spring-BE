package com.example.HansungCapstone.Controller;

import com.example.HansungCapstone.DTO.Es.EsDtoWrapper;
import com.example.HansungCapstone.DTO.HouseApply.HouseApply;
import com.example.HansungCapstone.Repository.Apply.Impl.APTApplyRepository;
import com.example.HansungCapstone.Service.Apply.APTApplyService;
import com.example.HansungCapstone.Service.Es.EsSearchService;

import com.example.HansungCapstone.Service.Es.TypoCurrectionService;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Getter
@RestController
@CrossOrigin(origins = {
        "http://localhost:3000", // 로컬 개발 환경
        "http://13.125.234.8:3000" // 배포환경
})
public class SearchController {

    @Autowired
    private EsSearchService esSearchService;

    private List<EsDtoWrapper> searchResult = new ArrayList<>();

    @Autowired
    private APTApplyRepository aptApplyRepository;

    @Autowired
    private APTApplyService aptApplyService;

    @Autowired
    private TypoCurrectionService typoCurrectionService;

    @GetMapping("/search")
    public ResponseEntity<List<EsDtoWrapper>> search(@RequestParam String query) throws IOException {
        boolean isQueryChanged = false;

        // 오탈차 전처리
        String newQuery = typoCurrectionService.correctTypo(query);

        // 오탈자 수정으로 인해 변경이 발생하면, 해당 사항을 프론트에 전달
        isQueryChanged = !newQuery.isEmpty();

        // 검색
        searchResult = esSearchService.search(query);

        // Custom header 추가
        HttpHeaders headers = new HttpHeaders();
        headers.add("isQueryChanged", String.valueOf(isQueryChanged));
        if (isQueryChanged) {
            headers.add("suggestQuery", URLEncoder.encode(newQuery, StandardCharsets.UTF_8));
        }

        return new ResponseEntity<>(searchResult, headers, HttpStatus.OK);
    }

    @GetMapping("/getAllApply")
    public List<HouseApply> getAllApply() throws IOException {
        //return aptApplyRepository.getApplies();
        return aptApplyService.getSavedApplies();
    }

    @Scheduled(cron = "0 0 18 * * *") // 매일 18시마다 함수 실행
    @GetMapping("/saveApplies")
    public void saveApplies() throws IOException {
        aptApplyService.saveAppliesToDatabase();
    }

    @GetMapping("/findApply")
    public Optional<HouseApply> findApply(@RequestParam(name = "house_NM") String house_NM,
                                          @RequestParam(name = "hssply_ADRES") String hssply_ADES
                                          ) throws IOException{
       return aptApplyService.getApplyById(house_NM);
    }

    @GetMapping("/relatedWord")
    public List<String> findApply(@RequestParam(name = "query") String query) throws IOException {

        return esSearchService.getRelatedWords(query);
        //return new ArrayList<>();
    }

}
