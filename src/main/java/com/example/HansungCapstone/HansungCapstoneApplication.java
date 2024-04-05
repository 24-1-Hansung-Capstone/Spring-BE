package com.example.HansungCapstone;

import com.example.HansungCapstone.DTO.HouseApply.impl.APTApply;
import com.example.HansungCapstone.Repository.Apply.APTApplyRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class HansungCapstoneApplication {

	public static void main(String[] args) throws IOException {

//		APTApplyRepository aptApplyRepository = new APTApplyRepository();
//		aptApplyRepository.getAPTApplies();

		SpringApplication.run(HansungCapstoneApplication.class, args);
	}
}
