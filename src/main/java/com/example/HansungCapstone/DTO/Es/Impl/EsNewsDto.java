package com.example.HansungCapstone.DTO.Es.Impl;

import com.example.HansungCapstone.DTO.Es.EsDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.elasticsearch.annotations.Document;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(indexName = "news")
public class EsNewsDto implements EsDto {
    private String title;
    private String url;

    private String mainBody;
    private String date;
    private double score;

    @Override
    public String getPreview() {
        return mainBody.substring(0, 50);
    }

    @Override
    public String getCategory() {
        return "news";
    }
}
