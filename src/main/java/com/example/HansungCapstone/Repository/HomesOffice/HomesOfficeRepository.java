package com.example.HansungCapstone.Repository.HomesOffice;

import com.example.HansungCapstone.DTO.HomesOffice.Realty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface HomesOfficeRepository extends JpaRepository<Realty, Integer> {
    List<Realty> findRealtyByType(int type);
    Optional<Realty> findRealtyById(int id);
    List<Realty> findRealtyByName(String name);
    List<Realty> findRealtyByWriter(String writer);
    List<Realty> findAll();
}
