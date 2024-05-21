package com.example.HansungCapstone.Controller;

import com.example.HansungCapstone.DTO.HomesOffice.Realty;
import com.example.HansungCapstone.DTO.HomesOffice.RealtyDto;
import com.example.HansungCapstone.Service.HomesOffice.HomesOfficeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/CommunityPage")
@CrossOrigin(origins = {
        "http://localhost:3000", // 로컬 개발 환경
        "http://13.125.234.8:3000" // 배포환경
})
public class HomesOfficeController {

    @Autowired
    private HomesOfficeService homesOfficeService;

    @PostMapping("/register")
    public void register(@RequestBody RealtyDto realtyDto) {
        System.out.println(realtyDto.getType());
        homesOfficeService.register(realtyDto);
    }

    @GetMapping("/")
    public List<Realty> findAll() {
        return homesOfficeService.findAll();
    }

    @GetMapping("/name")
    public List<Realty> findByName(@RequestParam String name) {
        return homesOfficeService.findByName(name);
    }

    @GetMapping("/type")
    public List<Realty> findByType(@RequestParam int type) {
        return homesOfficeService.findByType(type);
    }

    @GetMapping("/writer")
    public List<Realty> findByWriter(@RequestParam String writer) {
        return homesOfficeService.findByWriter(writer);
    }
}
