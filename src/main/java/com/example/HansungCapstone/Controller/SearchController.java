package com.example.HansungCapstone.Controller;

import com.example.HansungCapstone.DTO.Es.EsDtoWrapper;
import com.example.HansungCapstone.DTO.HouseApply.HouseApply;
import com.example.HansungCapstone.Repository.Apply.Impl.APTApplyRepository;
import com.example.HansungCapstone.Service.Apply.APTApplyService;
import com.example.HansungCapstone.Service.Es.EsSearchService;

import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Getter
@RestController
@CrossOrigin(origins = {"http://localhost:3000","http://223.194.156.178:3000"}) // TODO : cors 설정 필요할 수 있음
public class SearchController {

    @Autowired
    private EsSearchService esSearchService;
    @Autowired
    private APTApplyRepository aptApplyRepository;
    @Autowired
    private APTApplyService aptApplyService;

    private List<EsDtoWrapper> searchResult = new ArrayList<>();

    @GetMapping("/search")
    public List<EsDtoWrapper> search(@RequestParam String query) throws IOException {
        searchResult = esSearchService.search(query);
        return searchResult;
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
    }

}
