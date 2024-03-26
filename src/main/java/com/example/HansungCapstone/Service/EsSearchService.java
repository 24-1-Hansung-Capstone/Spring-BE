package com.example.HansungCapstone.Service;

import com.example.HansungCapstone.DTO.Es.EsDtoWrapper;
import com.example.HansungCapstone.DTO.Es.Impl.EsBlogDto;
import com.example.HansungCapstone.Repository.EsBlogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class EsSearchService {
    @Autowired
    private EsBlogRepository esBlogRepository;

    public List<EsDtoWrapper> search(String query) throws IOException {
        List<EsBlogDto> esBlogDtos= esBlogRepository.search(query);
        List<EsDtoWrapper> esDtoWrappers = new ArrayList<>();

        for (EsBlogDto esBlogDto: esBlogDtos){
            EsDtoWrapper esDtoWrapper = new EsDtoWrapper();
            esDtoWrapper.setEsDto(esBlogDto);
            esDtoWrapper.setPreview(esBlogDto.getMainBody().substring(0, 10));
            esDtoWrappers.add(esDtoWrapper);
        }

        return esDtoWrappers;
    }


}
