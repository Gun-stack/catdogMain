package com.kosta.catdog.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.socket.TextMessage;

import com.kosta.catdog.entity.Designer;
import com.kosta.catdog.entity.Pet;
import com.kosta.catdog.entity.Reservation;
import com.kosta.catdog.entity.User;
import com.kosta.catdog.repository.ReservationRepository;
import com.kosta.catdog.repository.UserDslRepository;
import com.kosta.catdog.service.ReservationService;


@RestController
public class ResController {
	@Autowired
	private ReservationRepository reservationRepository;
	@Autowired
	private UserDslRepository userDslRepository;
	@Autowired
	private ReservationService reservationService;
	
	
	
	
	
	@PostMapping("/completereserve")
	public	ResponseEntity<String> CompleteReservation(
			@RequestPart(value="file", required = false) MultipartFile file,
			@RequestParam("text") String text,
			@RequestParam("num") Integer num
			){
		System.out.println("text = " +file);
		 TextMessage message = new TextMessage("예약이 완료됨");
		
		try {
			Reservation resv = reservationRepository.findById(num).get();
			resv.setStatus("완료");
			resv.setCompleteText(text);
			reservationService.CompleteReservation(resv, file);			
			
			
			return new ResponseEntity<String>("1",HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
			
		}
		
	}
	
	@GetMapping("/resinfobyuserid")
	public ResponseEntity<List<Reservation>> selectresbyuser(@RequestParam String userId){
		System.out.println(userId);
		List<Reservation> resList = userDslRepository.findReservationListByUserId(userId);
		if (resList !=null) {
			return new ResponseEntity<List<Reservation>>(resList,HttpStatus.OK);
		}else {
			return new ResponseEntity<List<Reservation>>(HttpStatus.NOT_FOUND);
		}
	}
	
	
	
	
	@GetMapping("/resinfobydesnum")
	public ResponseEntity<List<Reservation>> SelectByDes(@RequestParam Integer desNum, @RequestParam String date){
		System.out.println(date);
		System.out.println(desNum);
		try {
	        // 클라이언트에서 받은 문자열을 java.util.Date로 파싱
	        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	        java.util.Date utilDate = dateFormat.parse(date);

	        // java.util.Date를 java.sql.Date로 변환
	        java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
	        
	        // 여기서 sqlDate를 사용하여 로직 수행
	        List<Reservation> resList = userDslRepository.findReservationListByDesigner_AndDate(desNum, sqlDate);
	        if (resList != null) {
	            return new ResponseEntity<List<Reservation>>(resList, HttpStatus.OK);
	        } else {
	            return new ResponseEntity<List<Reservation>>(HttpStatus.NOT_FOUND);
	        }
	    } catch (ParseException e) {
	        // 예외 처리: 날짜 형식이 올바르지 않은 경우
	        e.printStackTrace();
	        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	    }
	}
	
	
	
	@GetMapping("/reservedetail")
	public ResponseEntity<Object> findPetByResvNum(@RequestParam("num") Integer num) {
		try {
			Reservation resv =reservationRepository.findById(num).get();	
			User user = userDslRepository.findById(resv.getUserId());
			Pet pet = userDslRepository.FindPetByuserIdAndPetName(user.getNum(), resv.getPetName());
			Designer des = userDslRepository.FindDesignerById(resv.getDesId());
			
			
			Map<String, Object> response = new HashMap<>();
		        response.put("resv", resv);
		        response.put("user",user);
		        response.put("pet", pet);
		        response.put("des", des);
		        
			
			return new ResponseEntity<Object>(response, HttpStatus.OK);
		} catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
		}
	}
	
	

	
	// 예약 등록 !!
		@PostMapping("/makereservation")
public ResponseEntity<Reservation> reservation(@RequestBody Reservation resvInfo) {
		try {	
			System.out.println( "resv : " +resvInfo);
			Reservation res = reservationService.makeReservation(resvInfo);
			return new ResponseEntity<Reservation>(res,HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Reservation>(HttpStatus.BAD_REQUEST);
				
		}
			
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
	
}
