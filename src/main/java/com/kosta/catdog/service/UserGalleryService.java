package com.kosta.catdog.service;

import java.io.OutputStream;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.web.multipart.MultipartFile;

import com.kosta.catdog.entity.UserGallery;

public interface UserGalleryService {
	// 디자이너 갤러리 등록
	void registerUserGallery(UserGallery userGallery , MultipartFile file) throws Exception;
	// 디자이너 갤러리 수정
	void modifyUserGallery(UserGallery userGallery) throws Exception;
	// 디자이널 갤러리 삭제
	void deleteUserGallery(Integer num) throws Exception;
	// 디자이너 갤러리 확인
	UserGallery findUserGallery(Integer num) throws Exception;
	// 디자이너 갤러리 리스트(메인화면 -> 갤러리 구경하기)
//	Slice<UserGallery> userGalleryListMainPage(Pageable pageable) throws Exception;
//	// 디자이너 갤러리 리스트(샵)
//	List<UserGallery> userGalleryListShopPage(Integer num, int offset, int limit) throws Exception;
//	// 디자이너 갤러리 리스트(디자이너)
//	List<UserGallery> userGalleryListDesignerPage(Integer num, int offset, int limit) throws Exception;
	
	void fileView(Integer num, OutputStream out) throws Exception;
}
