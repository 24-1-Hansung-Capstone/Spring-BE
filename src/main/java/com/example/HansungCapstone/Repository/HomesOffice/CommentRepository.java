package com.example.HansungCapstone.Repository.HomesOffice;

import com.example.HansungCapstone.DTO.HomesOffice.RealtyComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CommentRepository extends JpaRepository<RealtyComment, Long> {
    List<RealtyComment> findAllByRealty_Id(int RealtyId);

    Optional<RealtyComment> findRealtyCommentById(long commentId);

    void deleteRealtyCommentById(long commentId);
}
