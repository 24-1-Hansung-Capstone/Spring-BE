package com.example.HansungCapstone.Service.HomesOffice;

import com.example.HansungCapstone.DTO.HomesOffice.Realty;
import com.example.HansungCapstone.DTO.HomesOffice.RealtyComment;
import com.example.HansungCapstone.DTO.HomesOffice.RealtyDto;
import com.example.HansungCapstone.Repository.HomesOffice.HomesOfficeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class HomesOfficeService {

    @Autowired
    private HomesOfficeRepository homesOfficeRepository;

    public void register(RealtyDto realtyDto) {
        Realty realty = new Realty();
        realty.setName(realtyDto.getName());
        realty.setAddr(realtyDto.getAddr());
        realty.setWriter(realtyDto.getWriter());
        realty.setType(realtyDto.getType());
        realty.setContent(realtyDto.getContent());
        homesOfficeRepository.save(realty);
    }

    // 저장되어 있는 매물들 모두 검색
    public List<Realty> findAll() {
        System.out.println(homesOfficeRepository.findAll());
        return homesOfficeRepository.findAll();
    }

    // 거래유형으로 매물들을 검색하는 기능
    public List<Realty> findByType(int type) {
        return homesOfficeRepository.findRealtyByType(type);
    }

    // 제목으로 매물들을 검색하는 기능
    public List<Realty> findByName(String name) {
        return homesOfficeRepository.findRealtyByName(name);
    }

    public List<Realty> findByWriter(String writer) {
        return homesOfficeRepository.findRealtyByWriter(writer);
    }

    public List<Realty> deletePost(int realtyId) {
        Realty realty = homesOfficeRepository.findRealtyById(realtyId).orElseThrow(()-> {
            return new IllegalArgumentException("해당 매물글이 존재하지 않습니다. id=" + realtyId);
        });

        homesOfficeRepository.delete(realty);
        List<Realty> realtys = homesOfficeRepository.findAll();
        return realtys; //매물글 삭제후 나머지 매물글 반환
    }
}
