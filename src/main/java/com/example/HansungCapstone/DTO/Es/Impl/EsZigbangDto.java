package com.example.HansungCapstone.DTO.Es.Impl;

import com.example.HansungCapstone.DTO.Es.EsDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(indexName = "zigbang")
public class EsZigbangDto implements EsDto {

    private int danji_id;
    private String danji_name;

    private float totalScore;
    @Field(type = FieldType.Text)
    private String totalDesc;

    private float trafficScore;
    @Field(type = FieldType.Text)
    private String trafficDesc;

    private float careScore;
    @Field(type = FieldType.Text)
    private String careDesc;

    private float residentScore;
    @Field(type = FieldType.Text)
    private String residentDesc;

    private float aroundScore;
    @Field(type = FieldType.Text)
    private String aroundDesc;

    private double score;

    @Override
    public String getPreview() {
        return null;
    }

    @Override
    public String getCategory() {
        return "zigbang";
    }
}
