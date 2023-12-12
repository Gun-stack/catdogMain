package com.kosta.catdog.service;

import java.io.OutputStream;
import java.util.List;

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


	// 고객 리뷰 모아 보여주기(디자이너 기준, 최신 날짜순으로)
	List<Review> reviewListByDesignerOrderByDateDesc(Integer num, int offset, int limit) throws Exception;
	// 이미지 파일 가져오기
	void fileView(Integer num, OutputStream out) throws Exception;
	// 고객 리뷰 모아 보여주기(샵 기준, 최신 날짜순으로)
	List<Review> reviewListByShopOrderByDateDesc(Integer num, int offset, int limit) throws Exception;
	// 고객 리뷰 모아 보여주기(디자이너 기준, 최신 날짜순으로)
	List<Review> reviewListByDesignerOrderByDateDesc(Integer num, int offset, int limit) throws Exception;	
}
