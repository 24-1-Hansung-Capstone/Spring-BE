package com.example.HansungCapstone.Service.Homes;

import com.example.HansungCapstone.DTO.Homes.Realty;
import com.example.HansungCapstone.DTO.Homes.RealtyDto;
import com.example.HansungCapstone.DTO.Homes.Type;
import com.example.HansungCapstone.Repository.Homes.HomesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HomesService {

    @Autowired
    private HomesRepository homesRepository;

    public void register(RealtyDto realtyDto) {
        Realty realty = new Realty();
        realty.setName(realtyDto.getName());
        realty.setAddr(realtyDto.getAddr());
        realty.setType(realtyDto.getType());
        realty.setContent(realtyDto.getContent());
        homesRepository.save(realty);
        System.out.println("Realty is saved");
    }

    // 저장되어 있는 매물들 모두 검색
    public List<Realty> findAll() {
        return homesRepository.findAll();
    }

    // 거래유형으로 매물들을 검색하는 기능
    public List<Realty> findByType(Type type) {
        return homesRepository.findByType(type);
    }

    // 제목으로 매물들을 검색하는 기능
    public List<Realty> findByName(String name) {
        return homesRepository.findByName(name);
    }
}
