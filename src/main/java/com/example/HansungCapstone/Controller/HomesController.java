package com.example.HansungCapstone.Controller;

import com.example.HansungCapstone.DTO.Homes.Realty;
import com.example.HansungCapstone.DTO.Homes.Type;
import com.example.HansungCapstone.Service.Homes.HomesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = {"http://localhost:3000",}) // TODO : cors 설정 필요할 수 있음
public class HomesController {

    @Autowired
    private HomesService homesService;

//    @PostMapping("/CommunityPage")
//    public void register(@RequestBody RealtyDto realtyDto) {
//        homesService.register(realtyDto);
//    }

    @GetMapping("/CommunityPage")
    public List<Realty> findAll() {
        return homesService.findAll();
    }

    @GetMapping("/CommunityPage/name")
    public List<Realty> findByName(@RequestParam String name) {
        return homesService.findByName(name);
    }

    @GetMapping("/CommunityPage/type")
    public List<Realty> findByType(@RequestParam Type type) {
        return homesService.findByType(type);
    }
}
