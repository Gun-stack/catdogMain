package com.kosta.catdog.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kosta.catdog.entity.Reservation;
import com.kosta.catdog.entity.User;
import com.kosta.catdog.repository.ReservationRepository;
import com.kosta.catdog.repository.UserDslRepository;


@RestController
public class ResController {
	@Autowired
	private ReservationRepository reservationRepository;
	@Autowired
	private UserDslRepository userDslRepository;
	
	@GetMapping("/resinfo")
	public ResponseEntity<List<Reservation>> selectresbyuser(@RequestParam String userId){
		System.out.println(userId);
		List<Reservation> resList = userDslRepository.findReservationListByUserId(userId);
		if (resList !=null) {
			return new ResponseEntity<List<Reservation>>(resList,HttpStatus.OK);
		}else {
			return new ResponseEntity<List<Reservation>>(HttpStatus.NOT_FOUND);
		}
	}
//	
//	public ResponseEntity<User> userInfo(@RequestParam String id) {
//		System.out.println(id);
//		User user = userDslRepository.findById(id);
//		if (user != null) {
//	        return new ResponseEntity<>(user, HttpStatus.OK);
//	    } else {
//	        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//	    }
//	}
	
	
	
	// 예약내역 조회(미용사)
	public List<Reservation> selectresbydes(String num){
		System.out.println("selectRes !! ");
		return null;
	}
	// 예약내역 상세조회
	public Reservation detailres(String num) {
		System.out.println("detailRes !!");
		return null;
	}
	// 예약내역 수정
	public void modires(String reftext) {
		System.out.println("modiRes !!");
	}
	// 예약 
	public void reservation(Reservation res) {
		System.out.println("Reservation !!");
	}

}
