package com.kosta.catdog;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;

import com.kosta.catdog.entity.DesGallery;
import com.kosta.catdog.repository.DesGalleryRepository;
import com.kosta.catdog.repository.UserDslRepository;

@SpringBootTest
class CatdogApplicationTests {
	@Autowired
	UserDslRepository userDslRepository;
	@Autowired
	DesGalleryRepository desGalleryRepository;


	// @Test
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
	
	// @Test
	void desGalleryListMainPage() {
		try {
			Sort sort = Sort.by("date").descending();
			PageRequest pageRequest = PageRequest.of(0, 9, sort);
			Slice<DesGallery> desGalleryListMain = desGalleryRepository.findAll(pageRequest);
			System.out.println(desGalleryListMain);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	// @Test
	void desGalleryListShopPage() {
		try {
			List<DesGallery> desGalleryListShop = userDslRepository.findDesGalleryListShopPage(1, 0, 9);
			System.out.println(desGalleryListShop);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	// @Test
	void desGalleryListDesignerPage() {
		try {
			List<DesGallery> desGalleryListDesigner = userDslRepository.findDesGalleryListDesignerPage(2, 0, 9);
			System.out.println(desGalleryListDesigner);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}