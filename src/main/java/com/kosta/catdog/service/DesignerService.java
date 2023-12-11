package com.kosta.catdog.service;

import java.io.OutputStream;
import java.util.List;

import com.kosta.catdog.entity.DesGallery;
import com.kosta.catdog.entity.Designer;
import com.kosta.catdog.entity.Review;

public interface DesignerService {
	// 프로필 정보 등록
	void addDesignerInfo(Designer designer) throws Exception;
	// 프로필 정보 수정 - 화면 정해지면 작성할 것
	// void modifyDesignerInfo(Designer designer) throws Exception;
	// 디자이너가 등록한 사진 보여주기
	DesGallery desGalleryByDesigner(Integer num) throws Exception;
	// 디자이너가 등록한 사진 모아 보여주기
	List<DesGallery> desGalleryListByDesigner(String desId) throws Exception;
	// 고객 리뷰 모아 보여주기(최신 날짜순으로)
	List<Review> reviewListByDesignerOrderByDateDesc(Integer num) throws Exception;
	// 별점 산출(디자이너별 등록된 모든 리뷰를 대상으로 별점 평균 산출해서 보여주기)
	Double avgStarCountByDesigner(Integer num) throws Exception;
	
	
	//디자이너 프로필사진조회
	void fileView(Integer num, OutputStream out) throws Exception ;
}