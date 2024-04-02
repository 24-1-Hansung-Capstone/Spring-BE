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
    private String tags;

    private String photoURL;   //HyperLink

    private double score;

    @Override
    public String getPreview() {
        String preview;
        try {
            preview = description.substring(0, 30);
        }
        catch (IndexOutOfBoundsException iobe) {
            preview = description;
        }
        return preview;
    }

    @Override
    public String getCategory() {
        return "visitkorea";
    }
}
