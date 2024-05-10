package com.example.HansungCapstone.DTO.Es.Impl;

import com.example.HansungCapstone.DTO.Es.EsDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.elasticsearch.annotations.Document;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(indexName = "houseproducts")
public class EsHouseProductsDto implements EsDto {
    private String title;
    private String url;
    private String mode;
    private String price;
    private String desc;
    private String option;
    private String location;

    private double score;

    @Override
    public String getPreview() {
        return desc.substring(0, 50);
    }

    @Override
    public String getCategory() {
        return "houseproducts";
    }
}