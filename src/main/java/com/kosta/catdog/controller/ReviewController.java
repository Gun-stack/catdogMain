package com.kosta.catdog.controller;

import java.util.List;

import org.springframework.web.bind.annotation.RestController;

import com.kosta.catdog.entity.Review;

@RestController
public class ReviewController {

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
	
}
