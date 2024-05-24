package com.example.HansungCapstone.Controller;

import com.example.HansungCapstone.DTO.CommentRequest;
import com.example.HansungCapstone.DTO.HomesOffice.Realty;
import com.example.HansungCapstone.DTO.HomesOffice.RealtyComment;
import com.example.HansungCapstone.DTO.HomesOffice.RealtyDto;
import com.example.HansungCapstone.Service.HomesOffice.CommentService;
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
    @Autowired
    private CommentService commentService;

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

    @PostMapping("/sendComment")
    public void writeComment(@RequestBody CommentRequest commentRequest) {
        String writer = commentRequest.getWriter();
        int realtyId = commentRequest.getRealtyId();
        String comment = commentRequest.getComment();

        commentService.writeComment(writer, realtyId, comment);
    }

    //댓글조회
    @GetMapping("/receiveComments")
    public List<RealtyComment> sendComments(@RequestParam int realtyId) {
        return commentService.sendComments(realtyId);
    }

    @GetMapping("/deleteComment")
    public String deleteComment(int commentId){
        return commentService.deleteComment(commentId);
    }
}
