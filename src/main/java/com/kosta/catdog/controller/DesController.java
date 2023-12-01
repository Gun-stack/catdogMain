package com.kosta.catdog.controller;

import org.springframework.web.bind.annotation.RestController;

import com.kosta.catdog.entity.Designer;

@RestController
public class DesController {

	// 디자이너 정보 조회
	public Designer selectdes(String index) {
		System.out.println("selectDes !!");
		return null;
	}
	// 디자이너 등록
	public void regdes(Designer des) {
		System.out.println("redDes !!");
	}
	// 프로필 문구 수정
	public void modipro(String info) {
		System.out.println("modiProfile !!");
	}
	
}
