package com.kosta.catdog.service;

import java.util.Date;
import java.util.List;

import com.kosta.catdog.entity.Reservation;

public interface ReservationService {
	// 예약하기
	void makeReservation(Reservation reservation) throws Exception;
	// 예약 내용 확인
	Reservation findReservation(Integer num) throws Exception;
	// 날짜별 예약 일정 확인
	List<Reservation> reservationListByDesignerAndDate(Integer num, Date date) throws Exception;
	// 예약 내용 수정
	void modifyReservation(Reservation reservation) throws Exception;
	// 예약 취소
	void cancelReservation(Integer num) throws Exception;
	
}
