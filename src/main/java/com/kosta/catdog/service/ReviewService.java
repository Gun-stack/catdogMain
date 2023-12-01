package com.kosta.catdog.service;

import com.kosta.catdog.entity.Review;

public interface ReviewService {
	// 리뷰 등록
	void postReview(Review review) throws Exception;
	// 리뷰 확인
	Review findReview(Integer num) throws Exception;
	// 리뷰 수정
	void modifyReveiw(Review review) throws Exception;
}
