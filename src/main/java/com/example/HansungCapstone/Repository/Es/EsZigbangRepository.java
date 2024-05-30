package com.example.HansungCapstone.Repository.Es;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch._types.query_dsl.MatchQuery;
import co.elastic.clients.elasticsearch._types.query_dsl.Query;
import co.elastic.clients.elasticsearch._types.query_dsl.TextQueryType;
import co.elastic.clients.elasticsearch.core.SearchResponse;
import com.example.HansungCapstone.DTO.Es.EsDto;
import com.example.HansungCapstone.DTO.Es.Impl.EsBlogDto;
import com.example.HansungCapstone.DTO.Es.Impl.EsVisitkoreaDto;
import com.example.HansungCapstone.DTO.Es.Impl.EsZigbangDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class EsZigbangRepository {

    @Autowired
    private ElasticsearchClient elasticsearchClient;

    public List<EsDto> search(String query) throws IOException {
        ArrayList<EsDto> results = new ArrayList<>();

        Query byDanjiName = MatchQuery.of(m-> m
                .field("danji_name")
                .query(query)
        )._toQuery();

        Query byTotalDesc = MatchQuery.of(m-> m
                .field("totalDesc")
                .query(query)
        )._toQuery();

        Query byTrafficDesc = MatchQuery.of(m-> m
                .field("trafficDesc")
                .query(query)
        )._toQuery();

        Query byCareDesc = MatchQuery.of(m-> m
                .field("careDesc")
                .query(query)
        )._toQuery();

        Query byResidentDesc = MatchQuery.of(m-> m
                .field("residentDesc")
                .query(query)
        )._toQuery();

        Query byAroundDesc = MatchQuery.of(m-> m
                .field("aroundDesc")
                .query(query)
        )._toQuery();

//        SearchResponse<EsZigbangDto> search = elasticsearchClient.search(s -> s
//                        .index("zigbang")
//                        .query(q -> q
//                                .bool(b -> b
//                                        .should(byDanjiName)
//                                        .should(byTotalDesc)
//                                        .should(byTrafficDesc)
//                                        .should(byCareDesc)
//                                        .should(byResidentDesc)
//                                        .should(byAroundDesc)
//                                )),
//                EsZigbangDto.class);

        SearchResponse<EsZigbangDto> search = elasticsearchClient.search(s -> s
                        .index("zigbang")
                        .size(100000)
                        .query(q->q
                                .multiMatch(v -> v
                                        .fields("danji_name^2", "totalDesc")
                                        .type(TextQueryType.MostFields)
                                        .query(query)
                                )
                        )
                ,EsZigbangDto.class
        );

        for (var hit: search.hits().hits()) {
            EsZigbangDto res = hit.source();
            res.setScore(hit.score());
            results.add(res);
        }

        return results;
    }
}
