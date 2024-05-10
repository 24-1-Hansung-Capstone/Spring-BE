package com.example.HansungCapstone.Repository.Es;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch.core.SearchResponse;
import com.example.HansungCapstone.DTO.Es.EsDto;
import com.example.HansungCapstone.DTO.Es.Impl.EsHouseProductsDto;
import com.example.HansungCapstone.DTO.Es.Impl.EsNewsDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class EsHouseProductsRepository {
    @Autowired
    private ElasticsearchClient elasticsearchClient;

    public List<EsDto> search(String query) throws IOException {
        ArrayList<EsDto> results = new ArrayList<>();

        SearchResponse<EsHouseProductsDto> search = elasticsearchClient.search(s -> s
                        .index("houseproducts")
                        .size(10000)
                        .query(q -> q
                                .match(t -> t
                                        .field("location")
                                        .query(query)
                                )),
                EsHouseProductsDto.class);

        for (var hit: search.hits().hits()) {
            EsHouseProductsDto res = hit.source();
            res.setScore(hit.score());
            results.add(res);
            System.out.println(res);
        }

        return results;
    }

    public List<String> getRelatedWords(String query) throws IOException {
        return new ArrayList<>();
    }
}
