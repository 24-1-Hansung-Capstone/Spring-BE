package com.example.HansungCapstone.Repository.Apply;

import com.example.HansungCapstone.DTO.HouseApply.impl.APTApply;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HouseDBRepository<S extends APTApply> extends JpaRepository<APTApply, String> {
}
