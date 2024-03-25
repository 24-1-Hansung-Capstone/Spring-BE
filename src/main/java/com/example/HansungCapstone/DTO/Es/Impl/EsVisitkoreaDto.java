package com.example.HansungCapstone.DTO.Es.Impl;

import com.example.HansungCapstone.DTO.Es.EsDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(indexName = "visitkorea")
public class EsVisitkoreaDto implements EsDto {

    @Field(type = FieldType.Text)
    private String title;
    @Field(type = FieldType.Text)
    private String location;
    @Field(type = FieldType.Text)
    private String description;
    @Field(type = FieldType.Text)
    private String tag;
    @Field(type = FieldType.Text)
    private String closure;

    private String photo_url;   //HyperLink
}
