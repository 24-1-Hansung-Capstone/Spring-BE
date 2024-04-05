package com.example.HansungCapstone.Repository.Apply;

import com.example.HansungCapstone.DTO.HouseApply.impl.APTApply;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import jakarta.json.Json;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.protocol.HTTP;
import org.apache.tomcat.util.json.JSONParser;
import org.springframework.stereotype.Repository;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@Repository
public class APTApplyRepository {
    //https://api.odcloud.kr/api/ApplyhomeInfoDetailSvc/v1/getAPTLttotPblancDetail?page=1&perPage=10&cond%5BSUBSCRPT_AREA_CODE_NM%3A%3AEQ%5D=%EC%84%9C%EC%9A%B8&serviceKey=RtgvroJq6o%2BLNjOnkOOf%2FGR4b19cFQIRpDqG9pupSVd2U93lh4wGNiv6gdc5zegzEg2YkkUQ1h02%2B9uCcYGDPg%3D%3D
    private final String requestUrl= "https://api.odcloud.kr/api/ApplyhomeInfoDetailSvc/v1/getAPTLttotPblancDetail";
    private final String serviceKey = "RtgvroJq6o%2BLNjOnkOOf%2FGR4b19cFQIRpDqG9pupSVd2U93lh4wGNiv6gdc5zegzEg2YkkUQ1h02%2B9uCcYGDPg%3D%3D";

    private final String fullUrl = "https://api.odcloud.kr/api/ApplyhomeInfoDetailSvc/v1/getAPTLttotPblancDetail?page=1&perPage=10000&cond%5BSUBSCRPT_AREA_CODE_NM%3A%3AEQ%5D=%EC%84%9C%EC%9A%B8&" +
            "serviceKey=RtgvroJq6o%2BLNjOnkOOf%2FGR4b19cFQIRpDqG9pupSVd2U93lh4wGNiv6gdc5zegzEg2YkkUQ1h02%2B9uCcYGDPg%3D%3D";

    public List<APTApply> getAPTApplies() throws IOException {
        StringBuilder urlBuilder = new StringBuilder(fullUrl);

        URL url = new URL(urlBuilder.toString());
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();

        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-type", "application/json");
        System.out.println("Response code: " + conn.getResponseCode());

        BufferedReader rd;

        if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        }
        else {
            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
        }

        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = rd.readLine()) != null) {
            sb.append(line);
        }

        rd.close();
        conn.disconnect();

        // Gson 인스턴스 생성
        Gson gson = new Gson();

        // JSON 문자열에서 data 배열을 추출하기 위한 JsonObject 사용
        JsonObject jsonObject = gson.fromJson(sb.toString(), JsonObject.class);
        JsonArray dataArray = jsonObject.getAsJsonArray("data");

        // data 배열의 타입 정의
        Type listType = new TypeToken<List<APTApply>>() {}.getType();

        // JsonArray를 List<Person>으로 변환
        List<APTApply> aptApplies = gson.fromJson(dataArray, listType);



        return aptApplies;
    }




}
