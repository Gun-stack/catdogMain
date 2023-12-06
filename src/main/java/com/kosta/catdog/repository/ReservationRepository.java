package com.kosta.catdog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kosta.catdog.entity.Reservation;

public interface ReservationRepository extends JpaRepository<Reservation, Integer> {
	Reservation findReservationByNum(Integer num);

}
