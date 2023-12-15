package com.kosta.catdog.controller;

import java.sql.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.kosta.catdog.entity.UserGalleryComment;
import com.kosta.catdog.repository.UserGalleryCommentRepository;
import com.kosta.catdog.repository.UserGalleryRepository;
import com.kosta.catdog.service.UserGalleryCommentService;

public class UserGalleryCommentController {
	@Autowired
	private UserGalleryCommentRepository userGalleryCommentRepository;
	@Autowired
	private UserGalleryCommentService userGalleryCommentService;
	@Autowired
	private UserGalleryRepository userGalleryRepository;
	
	@PostMapping("/userGallery/{num}/comment/{num}")
	public ResponseEntity<UserGalleryComment> commentreg(
			@RequestParam("content") String content,
			@RequestParam("date") Date date) {
		try {
			UserGalleryComment comment = new UserGalleryComment();
			comment.setContent(content);
			comment.setDate(date);
			System.out.println(comment);
			return new ResponseEntity<UserGalleryComment>(comment, HttpStatus.OK);
		} catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<UserGalleryComment>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@PostMapping("/userGallery/{num}/comment/{num}")
	public ResponseEntity<UserGalleryComment> commentmodi(
			@RequestParam("content") String content,
			@RequestParam("date") Date date) {
		try {
			UserGalleryComment comment = new UserGalleryComment();
			comment.setContent(content);
			comment.setDate(date);
			System.out.println(comment);
			return new ResponseEntity<UserGalleryComment>(comment, HttpStatus.OK);
		} catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<UserGalleryComment>(HttpStatus.BAD_REQUEST);
		}
	}	
	
}
