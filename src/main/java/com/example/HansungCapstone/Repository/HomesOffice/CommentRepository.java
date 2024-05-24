package com.example.HansungCapstone.Repository.HomesOffice;

import com.example.HansungCapstone.DTO.HomesOffice.RealtyComment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CommentRepository extends JpaRepository<RealtyComment, Long> {
    List<RealtyComment> findAllByRealty(int RealtyId);
    Optional<RealtyComment> findByCommentId(int CommentId);
    void deleteByCommentId(int commentId);
}
