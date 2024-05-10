package com.example.HansungCapstone.Repository.Es;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch._types.aggregations.Aggregate;
import co.elastic.clients.elasticsearch.core.SearchResponse;
import com.example.HansungCapstone.DTO.Es.EsDto;
import com.example.HansungCapstone.DTO.Es.Impl.EsBlogDto;
import jakarta.json.Json;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public class EsBlogRepository{

    @Autowired
    private ElasticsearchClient elasticsearchClient;

    public List<EsDto> search(String query) throws IOException {
        ArrayList<EsDto> results = new ArrayList<>();

//        SearchResponse<EsBlogDto> search = elasticsearchClient.search(s -> s
//                        .index("blog")
//                        .query(q -> q
//                                .fuzzy(FuzzyQuery.of(FuzzyQuery)_)
//                                .term(t -> t
//                                        .field("mainBody")
//                                        .value(v -> v.stringValue(query))
//
//                                )
//                        ) ,
//                EsBlogDto.class);
        SearchResponse<EsBlogDto> search = elasticsearchClient.search(s -> s
                        .index("blog")
                        .size(10000)
                        .query(q -> q
                                .fuzzy(f -> f
                                        .field("title")
                                        .field("mainBody")
                                        .value(query)
                                        .fuzziness("AUTO")
                                )
                        ),
                EsBlogDto.class);


        for (var hit: search.hits().hits()) {
            EsBlogDto res = hit.source();
            res.setScore(hit.score());
            results.add(res);
        }

        return results;
    }

    public List<String> getRelatedWords(String query) throws IOException {
        SearchResponse<EsBlogDto> search = elasticsearchClient.search(s -> s
                        .index("blog")
                        .size(10000)
                        .query(q -> q
                                .fuzzy(f -> f
                                        .field("title")
                                        .field("mainBody")
                                        .value(query)
                                        .fuzziness("AUTO")
                                )
                        )
                        .aggregations("relatedWord", a-> a
                                .significantTerms(t -> t
                                        .field("mainBody")
                                )
                        ),
                EsBlogDto.class);

        List<String> relatedWords = new ArrayList<>();
        for(var buc:  search.aggregations().get("relatedWord").sigsterms().buckets().array()){
            //버켓 스코어 평균 이상만 뽑도록 바꿀 것
            if(buc.score() > 1.1 && !query.equals(buc.key())) relatedWords.add(buc.key());
        }

        return relatedWords;
    }

}
