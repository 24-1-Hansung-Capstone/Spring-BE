package com.example.HansungCapstone.DTO.Es.Impl;

import com.example.HansungCapstone.DTO.Es.EsDto;
import lombok.*;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Document(indexName = "visitkorea")
public class EsVisitkoreaDto implements EsDto {
    private String title;
    private String location;
    private String description;
    private String tags;
    private String photoURL;

    private double score;

    @Override
    public String getPreview(String query) {
        return this.description;
    }

    @Override
    public String getCategory() {
        return "visitkorea";
    }
}
