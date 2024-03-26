package com.example.HansungCapstone.Controller;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch._types.mapping.FieldType;
import co.elastic.clients.elasticsearch.core.SearchResponse;
import co.elastic.clients.json.jackson.JacksonJsonpMapper;
import co.elastic.clients.transport.rest_client.RestClientTransport;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/testapi")
public class TestController {
    @GetMapping("/gettest")
    public String getTest() {
        return "Get Well";
    }

    @PostMapping("/posttest")
    public String postTest() {
        return "Post Well";
    }


    @Autowired
    private ElasticSearchConfig elasticSearchConfig;
    @GetMapping("/searchTest")
    public String searchTest(@RequestParam String query) throws IOException {
        StringBuilder result = new StringBuilder("input query : " + query + "<br>");

        ElasticsearchClient esClient = new ElasticsearchClient(
                new RestClientTransport(
                        elasticSearchConfig.restClient(),
                        new JacksonJsonpMapper()
                )
        );

//        try {
            SearchResponse<Test> search = esClient.search(s -> s
                            .index("*")
                            .query(q -> q
                                    .term(t -> t
                                            .field("main")
                                            .field("title")
                                            .value(v -> v.stringValue(query))
                                    )),
                    Test.class);
            for (var hit: search.hits().hits()) {
                result.append(hit.source().title + " : " + hit.source().main + "<br>");
            }
//        }
//        catch (IOException ioe) {
//            result.append("IOException");
//        }
//        catch (NullPointerException nlpe) {
//            result.append("NullPointerException");
//        }

        return result.toString();
    }

    @Document(indexName = "test")
    @NoArgsConstructor
    @AllArgsConstructor
    @Setter
    public static class Test{
        private String title;
        private String main;
    }
}
