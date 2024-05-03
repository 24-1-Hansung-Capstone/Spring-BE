package com.example.HansungCapstone.Repository.HomesOffice;

import com.example.HansungCapstone.DTO.HomesOffice.Realty;
import com.example.HansungCapstone.DTO.HomesOffice.Type;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HomesOfficeRepository extends JpaRepository<Realty, Integer> {
    List<Realty> findByType(Type type);
    List<Realty> findByName(String name);
}
