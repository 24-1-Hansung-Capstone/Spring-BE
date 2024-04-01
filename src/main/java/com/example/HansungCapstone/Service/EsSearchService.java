package com.example.HansungCapstone.Service;

import com.example.HansungCapstone.DTO.Es.EsDto;
import com.example.HansungCapstone.DTO.Es.EsDtoWrapper;
import com.example.HansungCapstone.DTO.Es.Impl.EsBlogDto;
import com.example.HansungCapstone.DTO.Es.Impl.EsNewsDto;
import com.example.HansungCapstone.DTO.Es.Impl.EsVisitkoreaDto;
import com.example.HansungCapstone.Repository.EsBlogRepository;
import com.example.HansungCapstone.Repository.EsNewsRepository;
import com.example.HansungCapstone.Repository.EsVisitkoreaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
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
        //esDtos.addAll(esVisitkoreaRepository.search(query));

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

        return esDtoWrappers;
    }

    private static void addBlogResults(List<EsDto> esBlogDtos, List<EsDtoWrapper> esDtoWrappers) {
        for (EsDto esBlogDto: esBlogDtos){
            //create wrapper
            EsDtoWrapper esDtoWrapper = new EsDtoWrapper();

            //set wrapper
            esDtoWrapper.setEsDto(esBlogDto);
            esDtoWrapper.setPreview(((EsBlogDto)esBlogDto).getMainBody().substring(0, 10));
            esDtoWrapper.setCategory("blog");

            //add results
            esDtoWrappers.add(esDtoWrapper);
        }
    }

}
