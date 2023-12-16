package com.kosta.catdog.service;

import java.sql.Date;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.kosta.catdog.entity.Reservation;

public interface ReservationService {
	// 예약하기
	Reservation makeReservation(Reservation reservation) throws Exception;
	
	void CompleteReservation(Reservation reservation ,MultipartFile file) throws Exception;
	// 예약 내용 확인(예약 번호로 조회)
	Reservation findReservation(Integer num) throws Exception;
	// 예약 내용 확인(유저 아이디로 조회)
	// 날짜별 예약 일정 확인
	List<Reservation> reservationListByDesignerAndDate(Integer num, Date date) throws Exception;
	// 예약 내용 수정
	void modifyReservation(Reservation reservation) throws Exception;
	// 예약 취소
	void cancelReservation(Integer num) throws Exception;

	
}
