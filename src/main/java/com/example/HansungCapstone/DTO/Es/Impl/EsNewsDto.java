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
    public String getPreview(String query) {
        if (mainBody == null || query == null || !mainBody.contains(query)) {
            return mainBody != null ? mainBody.substring(0, Math.min(50, mainBody.length())) : "";
        }

        int queryIndex = mainBody.indexOf(query);
        int previewLength = 50; // Total length of the preview
        int queryLength = query.length();

        int start = Math.max(0, queryIndex - (previewLength - queryLength) / 2);
        int end = Math.min(mainBody.length(), queryIndex + queryLength + (previewLength - queryLength) / 2);

        // Adjust start and end if the substring is shorter than the preview length
        if (end - start < previewLength) {
            if (start == 0) {
                end = Math.min(mainBody.length(), start + previewLength);
            } else if (end == mainBody.length()) {
                start = Math.max(0, end - previewLength);
            }
        }
        String preview = mainBody.substring(start, end);
        System.out.println("query: "+ preview);
        return preview;
    }

    @Override
    public String getCategory() {
        return "news";
    }
}
