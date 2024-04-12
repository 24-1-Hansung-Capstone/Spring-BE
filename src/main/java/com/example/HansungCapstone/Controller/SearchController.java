package com.example.HansungCapstone.Controller;

import com.example.HansungCapstone.DTO.Es.EsDtoWrapper;
import com.example.HansungCapstone.DTO.HouseApply.HouseApply;
import com.example.HansungCapstone.Repository.Apply.Impl.APTApplyRepository;
import com.example.HansungCapstone.Service.Apply.APTApplyService;
import com.example.HansungCapstone.Service.Es.EsSearchService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = {"http://localhost:3000",}) // TODO : cors 설정 필요할 수 있음
public class SearchController {

    @Autowired
    private EsSearchService esSearchService;
    @Autowired
    private APTApplyRepository aptApplyRepository;
    @Autowired
    private APTApplyService aptApplyService;


    @GetMapping("/search")
    public List<EsDtoWrapper> search(@RequestParam String query) throws IOException {
        return esSearchService.search(query);
    }

    @GetMapping("/apt")
    public List<HouseApply> apiTest() throws IOException {
        return aptApplyRepository.getApplies();
    }

    @Scheduled(cron = "0 0 18 * * *") // 매일 18시마다 함수 실행
    @GetMapping("/saveApplies")
    public void saveApplies() throws IOException {
        aptApplyService.saveAppliesToDatabase();
    }

    @GetMapping("/findApply")
    public Optional<HouseApply> findApply() throws IOException{
       return aptApplyService.getApplyById("포제스 한강");
    }
}
