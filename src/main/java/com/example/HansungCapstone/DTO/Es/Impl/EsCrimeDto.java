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
@Document(indexName = "crime")
public class EsCrimeDto implements EsDto {

    @Field(type = FieldType.Text)
    private String location;

    private float sum_generation;
    private float sum_arrest;
    private float murder_generation;
    private float murder_arrest;
    private float robbery_generation;
    private float robbery_arrest;
    private float rape_generation;
    private float rape_arrest;
    private float theft_generation;
    private float theft_arrest;
    private float violence_generation;
    private float violence_arrest;
}
