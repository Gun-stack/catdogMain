package com.kosta.catdog.controller;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.kosta.catdog.entity.Reservation;
import com.kosta.catdog.entity.Review;
import com.kosta.catdog.repository.ReservationRepository;
import com.kosta.catdog.repository.ReviewRepository;
import com.kosta.catdog.repository.UserDslRepository;
import com.kosta.catdog.service.ReviewService;

@RestController
public class ReviewController {
	
	@Autowired
	private ReviewRepository reviewRepository;
	@Autowired
	private ReviewService reviewService;
	@Autowired
	private UserDslRepository 	dslRepository;
	@Autowired
	private ReservationRepository reservationRepository;

	
	//리뷰등록
	@PostMapping("/reviewreg")
	public ResponseEntity<Boolean> ReviewReg(
			@RequestPart(value="file", required = false) MultipartFile file,
			@RequestParam("content") String content,
			@RequestParam("star") Integer star,
			@RequestParam("desId") String desId,
			@RequestParam("desNickname") String desNickname,
			@RequestParam("userNickname") String userNickname,
			@RequestParam("petName") String petName,
			@RequestParam("userId") String userId,
			@RequestParam("date") String date,
			@RequestParam("resNum") Integer resNum
			
			
			)
	{
	try {
		 	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	        java.util.Date utilDate = dateFormat.parse(date);
	        java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
		 Review review = new Review();
		 review.setUserId(userId);
		 review.setDesId(desId);
		 review.setResNum(resNum);
		 review.setDesNickname(desNickname);
		 review.setUserNickname(userNickname);
		 review.setContent(content);
		 review.setStar(star);
		 review.setDate(sqlDate);
		 review.setPetName(petName);
		 System.out.println(review);
		 
		 if (file !=null) {
			reviewService.registerReview(review, file);
		}else {
			reviewService.registerReview(review, null);
		}
		return new ResponseEntity<Boolean>(true,HttpStatus.OK);
	} catch (Exception e) {
		e.printStackTrace();
		return new ResponseEntity<Boolean>(HttpStatus.BAD_REQUEST);
			
	}
		
}
	//리뷰수정
	@PostMapping("/reviewmodi")
	public ResponseEntity<Boolean> modiReview(
			@RequestPart(value="file", required = false) MultipartFile file,
			@RequestParam("content") String content,
			@RequestParam("star") Integer star,
			@RequestParam("desId") String desId,
			@RequestParam("userId") String userId,
			@RequestParam("desNickname") String desNickname,
			@RequestParam("userNickname") String userNickname,
			@RequestParam("petName") String petName,
			@RequestParam("date") String date,
			@RequestParam("resNum") Integer resNum,
			@RequestParam("num") Integer num,
			@RequestParam("afterImg") String afterImg
			){
		try {
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	        java.util.Date utilDate = dateFormat.parse(date);
	        java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
		 Review review = new Review();
		 review.setNum(num);
		 review.setUserId(userId);
		 review.setDesId(desId);
		 review.setResNum(resNum);
		 review.setDesNickname(desNickname);
		 review.setUserNickname(userNickname);
		 review.setContent(content);
		 review.setStar(star);
		 review.setDate(sqlDate);
		 review.setPetName(petName);
		 if (file !=null) {
				reviewService.modifyReveiw(review, file);
			}else {
				review.setAfterImg(afterImg);
				reviewService.modifyReveiw(review, null);
			}
			return new ResponseEntity<Boolean>(true,HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Boolean>(HttpStatus.BAD_REQUEST);
				
		}
		
	}
		
	
	// 리뷰 조회(예약번호)
	@GetMapping("/reviewdetail")
	public ResponseEntity<Object> reviewByResNum(@RequestParam Integer resNum){
		System.out.println("resNum : "+ resNum);
		try {
			Review review =  dslRepository.FindReviewByResNum(resNum);
			Reservation res = reservationRepository.findById(resNum).get();
			Map<String, Object> response = new HashMap<>();
			response.put("res", res);
			response.put("review", review);
			
			return new ResponseEntity<Object>(response,HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
		}
	}
	//리뷰사진조회
	@GetMapping("/reviewimg/{num}")
	public void ImageView(@PathVariable Integer num, HttpServletResponse response) {
		try {
			reviewService.fileView(num, response.getOutputStream());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	
	
	
	
	
	// 리뷰 조회(샵)
	public List<Review> selreviewsshop(Integer num){
		System.out.println("sleReviewsShop !!");
		return null;
	}
	// 리뷰 조회(유저)
	public List<Review> selreviewsuser(Integer num){
		System.out.println("sleReviewsUser !!");
		return null;
	}
	// 리뷰 조회(미용사)
	public List<Review> selreviewsDes(Integer num){
		System.out.println("sleReviewsDes !!");
		return null;
	}
	
	// 디자이너별 리뷰 모아보기(한번에 3개씩 호출)
	// List<Review> reviewList = findReviewListByDesignerOrderByDateDesc(num, 0, 3);
	// 샵별 리뷰 모아보기(한번에 3개씩 호출)
	// List<Review> reviewList = findReviewListByShopOrderByDateDesc(num, 0, 3);
	
	@GetMapping("reviewlistbydes")
	public ResponseEntity<List<Review>> ReviewListByDes(@RequestParam
			Integer num, @RequestParam int offset,@RequestParam int limit){
		try {
			List<Review> reviewList = reviewService.reviewListByDesignerOrderByDateDesc(num, offset, limit);
			
			
			return new ResponseEntity<List<Review>> (reviewList ,HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<List<Review>> (HttpStatus.BAD_REQUEST);
		}
		
	}
	
	@GetMapping("reviewlistbyshop")
	public ResponseEntity<List<Review>> ReviewListByShop(@RequestParam
			Integer num, @RequestParam int offset,@RequestParam int limit){
		try {
			List<Review> reviewList = reviewService.reviewListByShopOrderByDateDesc(num, offset, limit);
			
			return new ResponseEntity<List<Review>> (reviewList ,HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<List<Review>> (HttpStatus.BAD_REQUEST);
		}
		
	}
	
	
	
	
}
