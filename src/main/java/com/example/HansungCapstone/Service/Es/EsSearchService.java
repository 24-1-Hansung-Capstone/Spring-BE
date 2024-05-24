package com.example.HansungCapstone.Service.Es;

import co.elastic.clients.elasticsearch._types.aggregations.SignificantStringTermsBucket;
import co.elastic.clients.elasticsearch.core.SearchResponse;
import com.example.HansungCapstone.DTO.Es.EsDto;
import com.example.HansungCapstone.DTO.Es.EsDtoWrapper;
import com.example.HansungCapstone.DTO.Es.Impl.EsBlogDto;
import com.example.HansungCapstone.Repository.Es.EsBlogRepository;
import com.example.HansungCapstone.Repository.Es.EsHouseProductsRepository;
import com.example.HansungCapstone.Repository.Es.EsNewsRepository;
import com.example.HansungCapstone.Repository.Es.EsVisitkoreaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

@Service
public class EsSearchService {
    @Autowired
    private EsBlogRepository esBlogRepository;

    @Autowired
    private EsNewsRepository esNewsRepository;

    @Autowired
    private EsVisitkoreaRepository esVisitkoreaRepository;

    @Autowired
    private EsHouseProductsRepository esHouseProductsRepository;

    public List<EsDtoWrapper> search(String query) throws IOException {
        //repo search
        List<EsDto> esDtos = new ArrayList<>();
        esDtos.addAll(esVisitkoreaRepository.search(query));
        esDtos.addAll(esBlogRepository.search(query));
        esDtos.addAll(esNewsRepository.search(query));
        esDtos.addAll(esHouseProductsRepository.search(query));

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


    public List<String> getRelatedWords(String query) throws IOException {
        List<String> relatedWords = new ArrayList<>();

        getRelatedWordsByScore(query, esBlogRepository.getRelatedBuckets(query), relatedWords);
        getRelatedWordsByScore(query, esNewsRepository.getRelatedBuckets(query), relatedWords);
        getRelatedWordsByScore(query, esHouseProductsRepository.getRelatedBuckets(query), relatedWords);

        // 반복문을 통해 요소를 처리
        for (int i = 0; i < relatedWords.size(); i++) {
            String nElement = relatedWords.get(i);

            // Iterator를 사용해 리스트 요소를 순회하며 삭제
            Iterator<String> iterator = relatedWords.iterator();
            while (iterator.hasNext()) {
                String currentElement = iterator.next();
                // 현재 요소가 n번째 요소와 같거나, n번째 요소의 일부 문자열을 포함하면 삭제
                if (currentElement.equals(nElement) || currentElement.contains(nElement)) {
                    iterator.remove();
                }
            }

            // n번째 요소는 다시 추가 (제거된 후 다시 추가하는 방식)
            relatedWords.add(i, nElement);
        }



        return relatedWords;
    }

    public List<String> getRelatedWordsByScore(String query, List<SignificantStringTermsBucket> buckets, List<String> relatedWords){
        buckets.sort((bucket1, bucket2) -> Double.compare(bucket2.score(), bucket1.score()));
        System.out.println(buckets);
        double total = 0;
        for(var buc : buckets){
            total += buc.score();
        }
        double avg = total / buckets.size();


        for(var buc : buckets){
            int centi = 0;

            if (query.equals(buc.key())){
                continue;
            }

            for (String s : relatedWords){
                //들어갈 조건 검사
                if (buc.key().equals(s) || buc.key().length() == 1) {
                    centi = 1;
                    break;
                }
            }

            if(centi == 0){
                relatedWords.add(buc.key());
                //if(relatedWords.size() == 5) break;
            }
        }

        return relatedWords;
    }
}
