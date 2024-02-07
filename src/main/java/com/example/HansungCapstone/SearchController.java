package com.example.HansungCapstone;

import org.springframework.web.bind.annotation.*;

@RestController
//@CrossOrigin // TODO : cors 설정 필요할 수 있음
public class SearchController {

    @GetMapping("/search")
    public String search(@RequestParam("query") String query) {
        // TODO : 반환은 json으로 하도록 변경해야함

        return "검색어 " + query + "에 대한 검색결과입니다.";
    }
}
