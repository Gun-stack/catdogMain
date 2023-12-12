package com.kosta.catdog.service;

import java.io.OutputStream;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.kosta.catdog.entity.DesGallery;

public interface DesGalleryService {
	// 디자이너 갤러리 등록
	DesGallery registerDesGallery(DesGallery desGallery, List<MultipartFile> files) throws Exception;
	// 디자이너 갤러리 수정
	void modifyDesGallery(Integer num) throws Exception;
	// 디자이널 갤러리 삭제
	void deleteDesGallery(Integer num) throws Exception;
	// 디자이너 갤러리 확인
	DesGallery findDesGallery(Integer num) throws Exception;
	// 디자이너 갤러리 리스트(메인화면 -> 갤러리 구경하기)
	List<DesGallery> desGalleryListMainPage() throws Exception;
	// 디자이너 갤러리 리스트(샵)
	List<DesGallery> desGalleryListShopPage(Integer num, int offset, int limit) throws Exception;
	// 디자이너 갤러리 리스트(디자이너)
	List<DesGallery> desGalleryListDesignerPage(Integer num, int offset, int limit) throws Exception;
	// 이미지 파일 가져오기
	void fileView(Integer num, OutputStream out) throws Exception;
}
