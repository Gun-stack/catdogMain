package com.kosta.catdog.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kosta.catdog.entity.Reservation;
import com.kosta.catdog.repository.ReservationRepository;
import com.kosta.catdog.repository.UserDslRepository;

@Service
public class ReservationServiceImpl implements ReservationService {

	@Autowired
	private UserDslRepository userDslRepository;
	@Autowired
	private ReservationRepository reservationRepository;
	
	@Override
	public void makeReservation(Reservation reservation) throws Exception {
		reservationRepository.save(reservation);
	}

	@Override
	public Reservation findReservation(Integer num) throws Exception {
		return reservationRepository.findReservation(num);
	}
	
	@Override
	public List<Reservation> reservationListByDesignerAndDate(Integer num, Date date) throws Exception {
		return userDslRepository.findReservationListByDesignerAndDate(num, date);
	}

	@Override
	public void modifyReservation(Reservation reservation) throws Exception {
		reservationRepository.save(reservation);
	}

	@Override
	public void cancelReservation(Integer num) throws Exception {
		reservationRepository.deleteById(num);
	}
}
