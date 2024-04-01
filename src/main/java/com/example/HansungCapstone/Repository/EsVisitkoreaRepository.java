package com.example.HansungCapstone.Repository;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
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

        SearchResponse<EsVisitkoreaDto> search = elasticsearchClient.search(s -> s
                        .index("visitkorea")
                        .query(q -> q
                                .term(t -> t
                                        .field("location")
                                        .value(v -> v.stringValue(query))
                                )),
                EsVisitkoreaDto.class);

        for (var hit: search.hits().hits()) {
            results.add(hit.source());
        }

        return results;
    }

}
