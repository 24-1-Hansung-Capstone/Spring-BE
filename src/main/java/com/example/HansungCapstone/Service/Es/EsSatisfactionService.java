package com.example.HansungCapstone.Service.Es;

import com.example.HansungCapstone.DTO.Es.EsDto;
import com.example.HansungCapstone.DTO.Es.EsDtoWrapper;
import com.example.HansungCapstone.Repository.Es.EsZigbangRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class EsSatisfactionService {
    @Autowired
    private EsZigbangRepository esZigbangRepository;

    public List<EsDto> search(String query) throws IOException {

        List<EsDto> esDtos = new ArrayList<>();
        esDtos.addAll(esZigbangRepository.search(query));

        esDtos.sort(new Comparator<EsDto>() {
            @Override
            public int compare(EsDto o1, EsDto o2) {
                // score를 비교하여 내림차순으로 정렬
                return Double.compare(o2.getScore(), o1.getScore());
            }
        });

        return esDtos;
    }



}
