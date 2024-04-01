package com.example.HansungCapstone.Repository;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch.core.SearchResponse;
import com.example.HansungCapstone.DTO.Es.EsDto;
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
                        .query(q -> q
                                .term(t -> t
                                        .field("mainBody")
                                        .value(v -> v.stringValue(query))
                                )),
                EsNewsDto.class);

        for (var hit: search.hits().hits()) {
            results.add(hit.source());
        }

        return results;
    }

}
