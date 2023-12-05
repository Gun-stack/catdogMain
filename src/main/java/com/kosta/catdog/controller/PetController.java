package com.kosta.catdog.controller;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.kosta.catdog.entity.Pet;
import com.kosta.catdog.service.PetService;

@RestController
public class PetController {
	@Autowired
	private PetService petService;
	
	
	@PostMapping("/petreg")
	public ResponseEntity<Boolean> petreg (
			@RequestPart(value="file", required = false) List<MultipartFile> file,
			@RequestParam("name") String name,
			@RequestParam("dogOrCat") Boolean dogOrCat,
			@RequestParam("age") Integer age,
			@RequestParam("weight") String weight,
			@RequestParam("breed") String breed,
			@RequestParam("gender") Boolean gender,
			@RequestParam("neuter") Boolean neuter
			) 
	{
		try {
			Pet pet =new Pet();
				pet.setName(name);
		        pet.setDogOrCat(dogOrCat);  // Corrected type casting
		        pet.setAge(age);  // Corrected type casting
		        pet.setWeight(new BigDecimal(weight));
		        pet.setBreed(breed);
		        pet.setGender(gender);
		        pet.setNeuter(neuter);
		        System.out.println(pet);
		        System.out.println(file);
		        
		        if (file != null && !file.isEmpty()) {
		            petService.petReg(pet, file);
		        } else {
		            petService.petReg(pet, null);  // 또는 파일을 처리하지 않는 메서드 호출
		        }
			
			return new ResponseEntity<Boolean>(true,HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Boolean>(false,HttpStatus.BAD_REQUEST);
		}
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
