package com.example.HansungCapstone.Controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/testapi")
public class TestController {

    @GetMapping("/gettest")
    public String getTest() {
        return "Get Well";
    }

    @PostMapping("/posttest")
    public String postTest() {
        return "Post Well";
    }
}
