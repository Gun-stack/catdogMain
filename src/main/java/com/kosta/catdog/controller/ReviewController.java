package com.kosta.catdog.controller;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.kosta.catdog.entity.Pet;
import com.kosta.catdog.entity.Review;
import com.kosta.catdog.service.ReviewService;

@RestController
public class ReviewController {
	
	@Autowired
	private ReviewService reviewService;

	// 리뷰 조회(샵)
	public List<Review> selreviewsshop(Integer num){
		System.out.println("sleReviewsShop !!");
		return null;
	}
	// 리뷰 조회(유저)
	public List<Review> selreviewsuser(Integer num){
		System.out.println("sleReviewsUser !!");
		return null;
	}
	// 리뷰 조회(미용사)
	public List<Review> selreviewsDes(Integer num){
		System.out.println("sleReviewsDes !!");
		return null;
	}

	@PostMapping("/reviewreg")
	public ResponseEntity<Boolean> reviewreg (
			@RequestPart(value="file", required = false) List<MultipartFile> file,
			@RequestParam("star") Integer star,
			@RequestParam("content") String content
			)
	{
		try {
			Review review =new Review();
				review.setStar(star);
				review.setContent(content);
		        System.out.println(review);
		        System.out.println(file);
		        
		        if (file != null && !file.isEmpty()) {
		        	reviewService.registerReview(review, file);
		        } else {
		            reviewService.registerReview(review, file);  // 또는 파일을 처리하지 않는 메서드 호출
		        }
			
			return new ResponseEntity<Boolean>(true,HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Boolean>(false,HttpStatus.BAD_REQUEST);
		}
	}
	
	
}
