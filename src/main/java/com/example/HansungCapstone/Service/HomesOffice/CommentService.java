package com.example.HansungCapstone.Service.HomesOffice;

import com.example.HansungCapstone.DTO.HomesOffice.Realty;
import com.example.HansungCapstone.DTO.HomesOffice.RealtyComment;
import com.example.HansungCapstone.Repository.HomesOffice.CommentRepository;
import com.example.HansungCapstone.Repository.HomesOffice.HomesOfficeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class CommentService  {

    private final CommentRepository commentRepository;
    private final HomesOfficeRepository homesOfficeRepository;

    //댓글 작성
    @Transactional
    public RealtyComment writeComment(String writer, int realtyId, String comment) {
        RealtyComment realtyComment = new RealtyComment();
        realtyComment.setComment(comment);

        // 게시판 번호로 게시글 찾기
        Realty realty = homesOfficeRepository.findById(realtyId).orElseThrow(() -> {
            return new IllegalArgumentException("게시판을 찾을 수 없습니다.");
        });

        realtyComment.setWriter(writer);
        realtyComment.setRealty(realty);
        commentRepository.save(realtyComment);

        return realtyComment;
    }

    // 글에 해당하는 전체 댓글 불러오기
    @Transactional(readOnly = true)
    public List<RealtyComment> sendComments(int realtyId) {
        List<RealtyComment> comments = commentRepository.findAllByRealty_Id(realtyId);

        return comments;
    }

    // 댓글 삭제하기
    @Transactional
    public List<RealtyComment> deleteComment(long commentId) {
        RealtyComment comment = commentRepository.findRealtyCommentById(commentId).orElseThrow(()-> {
            return new IllegalArgumentException("해당 댓글이 존재하지 않습니다. id=" + commentId);
        });

        int realtyId = comment.getRealty().getId();
        commentRepository.delete(comment);
        List<RealtyComment> realtyComments = commentRepository.findAllByRealty_Id(realtyId);
        return realtyComments; //댓글 삭제 후 댓글목록 가져오기
    }
}
