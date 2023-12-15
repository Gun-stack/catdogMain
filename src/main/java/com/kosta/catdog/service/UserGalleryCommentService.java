package com.kosta.catdog.service;

import java.util.List;

import com.kosta.catdog.entity.UserGalleryComment;

public interface UserGalleryCommentService {
	// 등록
	UserGalleryComment registerComment(UserGalleryComment userGalleryComment) throws Exception;
	// 수정
	UserGalleryComment modifyComment(UserGalleryComment userGalleryComment) throws Exception;
	// 삭제
	void deleteComment(Integer num) throws Exception;
	// 호출
	List<UserGalleryComment> findComment(Integer num) throws Exception;
}
