package com.kosta.catdog.service;

import java.io.OutputStream;

import org.springframework.web.multipart.MultipartFile;

import com.kosta.catdog.entity.Review;

public interface ReviewService {
	// 리뷰 등록
	Review postReview(Review review, MultipartFile file) throws Exception;
	// 리뷰 확인
	Review findReview(Integer num) throws Exception;
	// 리뷰 수정
	Review modifyReveiw(Review review ,MultipartFile file ) throws Exception;
	// 리뷰 삭제
	void deleteReview(Integer num) throws Exception;
	void fileView(Integer num, OutputStream out) throws Exception ;
}
