package com.example.HansungCapstone.Repository.Apply.Impl;

import com.example.HansungCapstone.DTO.HouseApply.HouseApply;
import com.example.HansungCapstone.DTO.HouseApply.impl.APTApply;
import com.example.HansungCapstone.Repository.Apply.HouseApplyRepository;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

@Repository
public class APTApplyRepository implements HouseApplyRepository {
    @Value("${houseApi.url}")
    private String requestUrl;

    @Value("${houseApi.sevicekey}")
    private String serviceKey;

    public List<HouseApply> getApplies() throws IOException {
        StringBuilder urlBuilder = makeGETUrl();
        StringBuilder sb = makeJSONstring(urlBuilder);
        if (sb == null) return null;
        List<HouseApply> aptApplies = getHouseAppliesFromJSONString(sb);
        return aptApplies;
    }

    public StringBuilder makeGETUrl() {
        StringBuilder urlBuilder = new StringBuilder(requestUrl)
                .append("/getAPTLttotPblancDetail")
                .append("?page=" + 1)
                .append("&perPage=" + 10000)
                .append("&cond%5BSUBSCRPT_AREA_CODE_NM%3A%3AEQ%5D=" + "%EC%84%9C%EC%9A%B8")  //.append("&cond[SUBSCRPT_AREA_CODE_NM::EQ]=" + "서울")
                .append("&serviceKey=" + serviceKey);
        return urlBuilder;
    }


    public StringBuilder makeJSONstring(StringBuilder urlBuilder) throws IOException {
        URL url = new URL(urlBuilder.toString());
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();

        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-type", "application/json");
        System.out.println("Response code: " + conn.getResponseCode());

        BufferedReader bufferedReader;

        if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
            bufferedReader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        } else {
            return null;
        }

        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            sb.append(line);
        }

        bufferedReader.close();
        conn.disconnect();
        return sb;
    }


    private static List<HouseApply> getHouseAppliesFromJSONString(StringBuilder sb) {
        Gson gson = new Gson();

        JsonObject jsonObject = gson.fromJson(sb.toString(), JsonObject.class);
        JsonArray dataArray = jsonObject.getAsJsonArray("data");

        Type listType = new TypeToken<List<APTApply>>() {}.getType();

        List<HouseApply> aptApplies = gson.fromJson(dataArray, listType);

        for(HouseApply aptApply : aptApplies) {
            System.out.println(((APTApply)aptApply).getHOUSE_NM());
        }
        return aptApplies;
    }
}
