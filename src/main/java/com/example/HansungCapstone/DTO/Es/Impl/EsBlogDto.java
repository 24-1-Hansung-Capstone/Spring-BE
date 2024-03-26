package com.example.HansungCapstone.DTO.Es.Impl;

import com.example.HansungCapstone.DTO.Es.EsDto;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.elasticsearch.annotations.Document;

import java.util.Date;

@Getter @Setter
@Document(indexName = "blog2")
public final class EsBlogDto implements EsDto {
    private String category;
    private String preview;

    private String title;
    private String url;

    private String mainBody;
    private Date date;
}
