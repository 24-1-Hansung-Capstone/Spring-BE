package com.example.HansungCapstone.DTO.Es.Impl;

import com.example.HansungCapstone.DTO.Es.EsDto;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.elasticsearch.annotations.Document;

import java.util.Date;

@Getter @Setter
@Document(indexName = "blog")
public final class EsBlogDto implements EsDto {
    private String title;
    private String url;
    private String mainBody;
    private Date date;
    private double score;

    @Override
    public String getPreview() {
        return mainBody.substring(0, 50);
    }

    @Override
    public String getCategory() {
        return "blog";
    }
}
