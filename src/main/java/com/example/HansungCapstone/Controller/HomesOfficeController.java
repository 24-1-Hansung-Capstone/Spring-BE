package com.example.HansungCapstone.Controller;

import com.example.HansungCapstone.DTO.HomesOffice.Realty;
import com.example.HansungCapstone.DTO.HomesOffice.RealtyDto;
import com.example.HansungCapstone.DTO.HomesOffice.Type;
import com.example.HansungCapstone.Service.HomesOffice.HomesOfficeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/CommunityPage")
@CrossOrigin(origins = {"http://localhost:3000",}) // TODO : cors 설정 필요할 수 있음
public class HomesOfficeController {

    @Autowired
    private HomesOfficeService homesOfficeService;

    @PostMapping("/register")
    public void register(@RequestBody RealtyDto realtyDto) {
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
    public List<Realty> findByType(@RequestParam Type type) {
        return homesOfficeService.findByType(type);
    }
}
