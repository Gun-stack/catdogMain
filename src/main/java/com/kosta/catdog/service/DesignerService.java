package com.kosta.catdog.service;

import java.util.Date;
import java.util.List;

import com.kosta.catdog.entity.DesGallery;
import com.kosta.catdog.entity.Designer;
import com.kosta.catdog.entity.Reservation;
import com.kosta.catdog.entity.Review;

public interface DesignerService {
	// 프로필 정보등록
	void addDesignerInfo(Designer designer) throws Exception;
	// 디자이너가 등록한 사진 모아 보여주기
	List<DesGallery> desGalleryListByDesigner(Integer num) throws Exception;
	// 고객 리뷰 모아 보여주기
	List<Review> reviewListByDesigner(Integer num) throws Exception;
	// 날짜별 예약 일정 확인
	List<Reservation> reservationListByDate(Date date) throws Exception;
	// 개별 예약 내용 확인
	Reservation findReservation(Integer num) throws Exception;
	
}
