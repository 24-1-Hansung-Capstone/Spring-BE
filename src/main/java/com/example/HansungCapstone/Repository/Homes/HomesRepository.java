package com.example.HansungCapstone.Repository.Homes;

import com.example.HansungCapstone.DTO.Homes.Realty;
import com.example.HansungCapstone.DTO.Homes.Type;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HomesRepository extends JpaRepository<Realty, Integer> {
    List<Realty> findByType(Type type);
    List<Realty> findByName(String name);
}
