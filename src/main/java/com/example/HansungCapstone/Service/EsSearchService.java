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
        List<EsDto> esBlogDtos = esBlogRepository.search(query);
        List<EsDto> esNewsDtos = esNewsRepository.search(query);
        List<EsDto> esVisitKoreaDtos = esVisitkoreaRepository.search(query);

        //results
        List<EsDtoWrapper> esDtoWrappers = new ArrayList<>();

        //esBlogDto add
        addBlogResults(esBlogDtos, esDtoWrappers);
        addNewsResults(esNewsDtos, esDtoWrappers);
        addVisitKoreaResults(esVisitKoreaDtos, esDtoWrappers);


        return esDtoWrappers;
    }

    private static void addNewsResults(List<EsDto> esNewsDtos, List<EsDtoWrapper> esDtoWrappers) {
        for (EsDto esNewsDto: esNewsDtos){
            //create wrapper
            EsDtoWrapper esDtoWrapper = new EsDtoWrapper();

            //set wrapper
            esDtoWrapper.setEsDto(esNewsDto);
            esDtoWrapper.setPreview(((EsNewsDto)esNewsDto).getMainBody().substring(0, 10));
            esDtoWrapper.setCategory("news");

            //add results
            esDtoWrappers.add(esDtoWrapper);
        }
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

    private static void addVisitKoreaResults(List<EsDto> esVisitKoreaDtos, List<EsDtoWrapper> esDtoWrappers) {
        for (EsDto esVisitKoreaDto: esVisitKoreaDtos){
            //create wrapper
            EsDtoWrapper esDtoWrapper = new EsDtoWrapper();

            //set wrapper
            esDtoWrapper.setEsDto(esVisitKoreaDto);
            esDtoWrapper.setPreview(((EsVisitkoreaDto)esVisitKoreaDto).getDescription().substring(0, 10));
            esDtoWrapper.setCategory("visitkorea");

            //add results
            esDtoWrappers.add(esDtoWrapper);
        }
    }


}
