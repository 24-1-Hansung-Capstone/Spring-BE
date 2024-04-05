package com.example.HansungCapstone.Repository.Es;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch.core.SearchResponse;
import com.example.HansungCapstone.DTO.Es.EsDto;
import com.example.HansungCapstone.DTO.Es.Impl.EsBlogDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class EsBlogRepository{

    @Autowired
    private ElasticsearchClient elasticsearchClient;

    public List<EsDto> search(String query) throws IOException {
        ArrayList<EsDto> results = new ArrayList<>();

        SearchResponse<EsBlogDto> search = elasticsearchClient.search(s -> s
                        .index("blog")
                        .query(q -> q
                                .term(t -> t
                                        .field("mainBody")
                                        .value(v -> v.stringValue(query))
                                )) ,
                EsBlogDto.class);

        for (var hit: search.hits().hits()) {
            EsBlogDto res = hit.source();
            res.setScore(hit.score());
            results.add(res);
        }

        return results;
    }

}
