package com.kosta.catdog;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;

import com.kosta.catdog.entity.DesGallery;
import com.kosta.catdog.entity.UserGalleryComment;
import com.kosta.catdog.repository.DesGalleryRepository;
import com.kosta.catdog.repository.UserDslRepository;
import com.kosta.catdog.repository.UserGalleryCommentRepository;

@SpringBootTest
class CatdogApplicationTests {
	@Autowired
	UserDslRepository userDslRepository;
	@Autowired
	DesGalleryRepository desGalleryRepository;
	@Autowired
	UserGalleryCommentRepository userGalleryCommentRepository;

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
	
	// @Test
	void findComment() {
		try {
			Optional<UserGalleryComment> comment = userGalleryCommentRepository.findById(1);
			System.out.println(comment);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
