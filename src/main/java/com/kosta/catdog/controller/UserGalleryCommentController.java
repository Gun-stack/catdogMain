package com.kosta.catdog.controller;

import java.sql.Date;
import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kosta.catdog.entity.User;
import com.kosta.catdog.entity.UserGallery;
import com.kosta.catdog.entity.UserGalleryComment;
import com.kosta.catdog.repository.UserGalleryCommentRepository;
import com.kosta.catdog.repository.UserGalleryRepository;
import com.kosta.catdog.repository.UserRepository;
import com.kosta.catdog.service.UserGalleryCommentService;

@RestController
public class UserGalleryCommentController {
	
	@Autowired
	private UserGalleryCommentRepository userGalleryCommentRepository;
//	@Autowired
//	private UserGalleryCommentService userGalleryCommentService;
	@Autowired
	private UserGalleryRepository userGalleryRepository;
	@Autowired 
	private UserRepository userRepository;
	
	@PostMapping("/usergallerycomment")
	public ResponseEntity<UserGalleryComment> commentreg(
			@RequestParam("userNum") Integer userNum,
			@RequestParam("galNum") Integer galNum,
			@RequestParam("content") String content
			) {
		try {
			System.out.println(userNum);
			System.out.println(galNum);
			System.out.println(content);
			
			User user = userRepository.findById(userNum).get();
			
			UserGalleryComment comment = new UserGalleryComment();
			comment.setUserNickname(user.getNickname());
			comment.setContent(content);
			comment.setUserId(user.getId());
			comment.setGalleryNum(galNum);
			Date currentDate = Date.valueOf(LocalDate.now());
			comment.setDate(currentDate);
			userGalleryCommentRepository.save(comment);
			
			UserGallery userGallery = userGalleryRepository.findById(galNum).get();
			userGallery.setCommentCnt(userGallery.getCommentCnt()+1);
			userGalleryRepository.save(userGallery);
			
			return new ResponseEntity<UserGalleryComment>(comment, HttpStatus.OK);
		} catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<UserGalleryComment>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@PostMapping("/usergallerycommentdelete")
	public ResponseEntity<Boolean> commentDelete(@RequestParam("commentNum") Integer commentNum) 
	{
		
		try {
			UserGalleryComment comment  = userGalleryCommentRepository.findById(commentNum).get();
			UserGallery gallery = userGalleryRepository.findById(comment.getGalleryNum()).get();
			
			gallery.setCommentCnt(gallery.getCommentCnt()-1);
			userGalleryCommentRepository.delete(comment);
			return new ResponseEntity<Boolean>(true, HttpStatus.OK);
		} catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Boolean>(HttpStatus.BAD_REQUEST);
		}
	}
	
//	
//	@PostMapping("/userGallery/{num}/comment/{num}")
//	public ResponseEntity<UserGalleryComment> commentmodi(
//			@RequestParam("content") String content,
//			@RequestParam("date") Date date) {
//		try {
//			UserGalleryComment comment = new UserGalleryComment();
//			comment.setContent(content);
//			comment.setDate(date);
//			System.out.println(comment);
//			return new ResponseEntity<UserGalleryComment>(comment, HttpStatus.OK);
//		} catch(Exception e) {
//			e.printStackTrace();
//			return new ResponseEntity<UserGalleryComment>(HttpStatus.BAD_REQUEST);
//		}
//	}	
//	
}
