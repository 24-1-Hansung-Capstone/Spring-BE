package com.example.HansungCapstone.Repository.Apply;

import com.example.HansungCapstone.DTO.HouseApply.HouseApply;
import com.example.HansungCapstone.DTO.HouseApply.impl.APTApply;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.List;


public interface HouseApplyRepository {
    public List<HouseApply> getApplies() throws IOException;
    public StringBuilder makeGETUrl();
    public StringBuilder makeJSONstring(StringBuilder urlBuilder) throws IOException;
}
