package com.kosta.catdog.service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.kosta.catdog.entity.UserGalleryComment;
import com.kosta.catdog.repository.UserDslRepository;
import com.kosta.catdog.repository.UserGalleryCommentRepository;
import com.kosta.catdog.repository.UserGalleryRepository;

public class UserGalleryCommentServiceImpl implements UserGalleryCommentService {
	@Autowired
	private UserDslRepository userDslRepository;
	@Autowired
	private UserGalleryCommentRepository userGalleryCommentRepository;
	@Autowired
	private UserGalleryCommentService userGalleryCommentService;
	@Autowired
	private UserGalleryRepository userGalleryRepository;
		
	@Override
	public UserGalleryComment registerComment(UserGalleryComment userGalleryComment) throws Exception {
		UserGalleryComment comment = new UserGalleryComment();
		Date date = Date.valueOf(LocalDate.now());
		comment.setContent(userGalleryComment.getContent());
		comment.setDate(date);
		userGalleryCommentRepository.save(comment);
		return comment;
	}

	@Override
	public UserGalleryComment modifyComment(UserGalleryComment userGalleryComment) throws Exception {
		Date date = Date.valueOf(LocalDate.now());
		Optional<UserGalleryComment> existingComment = userGalleryCommentRepository.findById(userGalleryComment.getNum());
	    
	    if (existingComment.isPresent()) {
	        UserGalleryComment comment = existingComment.get();
	        comment.setContent(userGalleryComment.getContent());
	        comment.setDate(Date.valueOf(LocalDate.now()));
	        return userGalleryCommentRepository.save(comment);
	    } else {
	        // 수정할 댓글을 찾지 못한 경우 처리
	        throw new Exception("댓글을 찾을 수 없습니다.");
	    }
	}

	@Override
	public void deleteComment(Integer num) throws Exception {
		userGalleryCommentRepository.deleteById(num);
	}

	@Override
	public List<UserGalleryComment> findComment(Integer num) throws Exception {
		return userDslRepository.findComment(num);
	}	

}
