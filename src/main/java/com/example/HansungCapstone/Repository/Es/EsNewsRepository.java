package com.example.HansungCapstone.Repository.Es;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch._types.aggregations.SignificantStringTermsBucket;
import co.elastic.clients.elasticsearch.core.SearchResponse;
import com.example.HansungCapstone.DTO.Es.EsDto;
import com.example.HansungCapstone.DTO.Es.Impl.EsBlogDto;
import com.example.HansungCapstone.DTO.Es.Impl.EsNewsDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class EsNewsRepository{

    @Autowired
    private ElasticsearchClient elasticsearchClient;

    public List<EsDto> search(String query) throws IOException {
        ArrayList<EsDto> results = new ArrayList<>();

        SearchResponse<EsNewsDto> search = elasticsearchClient.search(s -> s
                        .index("news")
                        .size(10000)
                        .query(q -> q
                                .term(t -> t
                                        .field("mainBody")
                                        .value(v -> v.stringValue(query))
                                )),
                EsNewsDto.class);

        for (var hit: search.hits().hits()) {
            EsNewsDto res = hit.source();
            res.setScore(hit.score());
            results.add(res);
        }

        return results;
    }

    public List<SignificantStringTermsBucket> getRelatedBuckets(String query) throws IOException {
        SearchResponse<EsNewsDto> search = elasticsearchClient.search(s -> s
                        .index("news")
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
                EsNewsDto.class);

        return search.aggregations().get("relatedWord").sigsterms().buckets().array();
    }

}
