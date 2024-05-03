package com.example.HansungCapstone.Service.HomesOffice;

import com.example.HansungCapstone.DTO.HomesOffice.Realty;
import com.example.HansungCapstone.DTO.HomesOffice.RealtyDto;
import com.example.HansungCapstone.DTO.HomesOffice.Type;
import com.example.HansungCapstone.Repository.HomesOffice.HomesOfficeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HomesOfficeService {

    @Autowired
    private HomesOfficeRepository homesOfficeRepository;

    public void register(RealtyDto realtyDto) {
        Realty realty = new Realty();
        realty.setName(realtyDto.getName());
        realty.setAddr(realtyDto.getAddr());
        realty.setType(realtyDto.getType());
        realty.setContent(realtyDto.getContent());
        homesOfficeRepository.save(realty);
        System.out.println("Realty is saved");
    }

    // 저장되어 있는 매물들 모두 검색
    public List<Realty> findAll() {
        return homesOfficeRepository.findAll();
    }

    // 거래유형으로 매물들을 검색하는 기능
    public List<Realty> findByType(Type type) {
        return homesOfficeRepository.findByType(type);
    }

    // 제목으로 매물들을 검색하는 기능
    public List<Realty> findByName(String name) {
        return homesOfficeRepository.findByName(name);
    }
}
