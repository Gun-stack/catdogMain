package com.kosta.catdog;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.kosta.catdog.repository.UserDslRepository;

@SpringBootTest
class CatdogApplicationTests {
	@Autowired
	UserDslRepository userDslRepository;


	//@Test

	void contextLoads() {
		
	}
	
	// @Test
	void avgStarCountByDesigner() {
		try {
			Double avgStarCount = userDslRepository.findAvgStarCountByDesigner(1);
			System.out.println(avgStarCount);
		} catch(Exception e) {
			e.printStackTrace();
		}		
	}
}