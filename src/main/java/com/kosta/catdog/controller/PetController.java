package com.kosta.catdog.controller;

import java.util.List;

import org.springframework.web.bind.annotation.RestController;

import com.kosta.catdog.entity.Pet;

@RestController
public class PetController {
	
	// 반려동물 등록
	public void petreg(Pet Pet) {
		System.out.println("petReg !!");
	}
	// 등록한 반려동물 조회
	public List<Pet> selectpet(Integer num){
		System.out.println("selectPet !!");
		return null;
	}
	// 반려동물 정보 수정
	public void modipet(Pet pet) {
		System.out.println("modiPet !!");
	}

}
