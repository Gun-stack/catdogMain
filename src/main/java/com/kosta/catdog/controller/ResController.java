package com.kosta.catdog.controller;

import java.util.List;

import org.springframework.web.bind.annotation.RestController;

import com.kosta.catdog.entity.Reservation;


@RestController
public class ResController {
	
	// 예약내역 조회(유저)
	public List<Reservation> selectresbyuser(String num){
		System.out.println("selectRes !! ");
		return null;
	}
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
