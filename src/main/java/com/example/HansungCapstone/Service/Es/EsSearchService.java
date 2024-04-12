package com.example.HansungCapstone.Service.Es;

import com.example.HansungCapstone.DTO.Es.EsDto;
import com.example.HansungCapstone.DTO.Es.EsDtoWrapper;
import com.example.HansungCapstone.DTO.Es.Impl.EsBlogDto;
import com.example.HansungCapstone.Repository.Es.EsBlogRepository;
import com.example.HansungCapstone.Repository.Es.EsNewsRepository;
import com.example.HansungCapstone.Repository.Es.EsVisitkoreaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class EsSearchService {
    @Autowired
    private EsBlogRepository esBlogRepository;

    @Autowired
    private EsNewsRepository esNewsRepository;

    @Autowired
    private EsVisitkoreaRepository esVisitkoreaRepository;

    public List<EsDtoWrapper> search(String query) throws IOException {
        //repo search
        List<EsDto> esDtos = new ArrayList<>();
        esDtos.addAll(esBlogRepository.search(query));
        //esDtos.addAll(esNewsRepository.search(query));
        esDtos.addAll(esVisitkoreaRepository.search(query));

        //results
        List<EsDtoWrapper> esDtoWrappers = new ArrayList<>();
        for (EsDto esDto: esDtos){
            //create wrapper
            EsDtoWrapper esDtoWrapper = new EsDtoWrapper();

            //set wrapper
            esDtoWrapper.setEsDto(esDto);
            esDtoWrapper.setPreview(esDto.getPreview());
            esDtoWrapper.setCategory(esDto.getCategory());

            //add results
            esDtoWrappers.add(esDtoWrapper);
        }

        esDtoWrappers.sort(new Comparator<EsDtoWrapper>() {
            @Override
            public int compare(EsDtoWrapper o1, EsDtoWrapper o2) {
                // score를 비교하여 내림차순으로 정렬
                return Double.compare(o2.esDto.getScore(), o1.esDto.getScore());
            }
        });

        return esDtoWrappers;
    }
}
