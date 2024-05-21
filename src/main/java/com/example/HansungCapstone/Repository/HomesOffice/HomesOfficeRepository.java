package com.example.HansungCapstone.Repository.HomesOffice;

import com.example.HansungCapstone.DTO.HomesOffice.Realty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HomesOfficeRepository extends JpaRepository<Realty, Integer> {
    List<Realty> findByType(int type);
    List<Realty> findByName(String name);
    List<Realty> findByWriter(String writer);
    List<Realty> findAll();
}
