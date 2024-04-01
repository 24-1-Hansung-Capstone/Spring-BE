package com.example.HansungCapstone.Repository;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch._types.query_dsl.MatchQuery;
import co.elastic.clients.elasticsearch._types.query_dsl.Query;
import co.elastic.clients.elasticsearch.core.SearchResponse;
import com.example.HansungCapstone.DTO.Es.EsDto;
import com.example.HansungCapstone.DTO.Es.Impl.EsVisitkoreaDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class EsVisitkoreaRepository{

    @Autowired
    private ElasticsearchClient elasticsearchClient;

    public List<EsDto> search(String query) throws IOException {
        ArrayList<EsDto> results = new ArrayList<>();

        Query byLocation = MatchQuery.of(m-> m
                .field("location")
                .query(query)
        )._toQuery();

        Query byTitle = MatchQuery.of(m-> m
                .field("title")
                .query(query)
        )._toQuery();

        Query byDescription = MatchQuery.of(m-> m
                .field("description")
                .query(query)
        )._toQuery();

        Query byTags = MatchQuery.of(m-> m
                .field("tags")
                .query(query)
        )._toQuery();

        SearchResponse<EsVisitkoreaDto> search = elasticsearchClient.search(s -> s
                        .index("visitkorea")
                        .query(q -> q
                                .bool(b -> b
                                        .should(byLocation)
                                        .should(byTitle)
                                        .should(byDescription)
                                        .should(byTags)
                                )),
                EsVisitkoreaDto.class);

        for (var hit: search.hits().hits()) {
            results.add(hit.source());
        }

        return results;
    }

}
