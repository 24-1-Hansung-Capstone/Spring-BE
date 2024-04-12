package com.example.HansungCapstone;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@EnableScheduling
@SpringBootTest
class HansungCapstoneApplicationTests {

	@Test
	void contextLoads() {
		System.out.println("auto download");
	}

	@Scheduled(fixedRate = 5000) // 5초마다 실행
	void runTestPeriodically() {
		contextLoads();
	}
}
