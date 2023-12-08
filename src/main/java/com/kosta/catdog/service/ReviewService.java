package com.kosta.catdog.service;

import java.io.OutputStream;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.kosta.catdog.entity.Review;

public interface ReviewService {
	// 리뷰 등록
	Review registerReview(Review review, List<MultipartFile> files) throws Exception;
	// 리뷰 확인
	Review findReview(Integer num) throws Exception;
	// 리뷰 수정
	void modifyReveiw(Review review) throws Exception;
	// 리뷰 삭제
	void deleteReview(Integer num) throws Exception;
	// 이미지 파일 가져오기
	void fileView(Integer num, OutputStream out) throws Exception;
}
