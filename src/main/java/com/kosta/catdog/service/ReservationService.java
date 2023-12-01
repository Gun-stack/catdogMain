package com.kosta.catdog.service;

import com.kosta.catdog.entity.Reservation;

public interface ReservationService {
	// 예약하기
	void makeReservation(Reservation reservation) throws Exception;
	// 예약 내용 확인
	Reservation findReservation(Integer num) throws Exception;
	// 예약 내용 수정
	void modifyReservation(Reservation reservation) throws Exception;
}
