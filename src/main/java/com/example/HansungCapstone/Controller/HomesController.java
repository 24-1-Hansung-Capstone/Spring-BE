package com.example.HansungCapstone.Controller;

import com.example.HansungCapstone.DTO.Homes.Realty;
import com.example.HansungCapstone.DTO.Homes.RealtyDto;
import com.example.HansungCapstone.DTO.Homes.Type;
import com.example.HansungCapstone.Service.Homes.HomesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/CommunityPage")
@CrossOrigin(origins = {"http://localhost:3000",}) // TODO : cors 설정 필요할 수 있음
public class HomesController {

    @Autowired
    private HomesService homesService;

    @PostMapping("/register")
    public void register(@RequestBody RealtyDto realtyDto) {
        homesService.register(realtyDto);
    }

    @GetMapping("/")
    public List<Realty> findAll() {
        return homesService.findAll();
    }

    @GetMapping("/name")
    public List<Realty> findByName(@RequestParam String name) {
        return homesService.findByName(name);
    }

    @GetMapping("/type")
    public List<Realty> findByType(@RequestParam Type type) {
        return homesService.findByType(type);
    }
}
