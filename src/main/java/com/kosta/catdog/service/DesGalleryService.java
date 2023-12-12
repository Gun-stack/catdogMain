package com.kosta.catdog.service;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import com.kosta.catdog.entity.DesGallery;

public interface DesGalleryService {
	// 디자이너 갤러리 등록
	void registerDesGallery(DesGallery desGallery) throws Exception;
	// 디자이너 갤러리 수정
	void modifyDesGallery(DesGallery desGallery) throws Exception;
	// 디자이널 갤러리 삭제
	void deleteDesGallery(Integer num) throws Exception;
	// 디자이너 갤러리 확인
	DesGallery findDesGallery(Integer num) throws Exception;
	// 디자이너 갤러리 리스트(메인화면 -> 갤러리 구경하기)
	Slice<DesGallery> desGalleryListMainPage(Pageable pageable) throws Exception;
	// 디자이너 갤러리 리스트(샵)
	List<DesGallery> desGalleryListShopPage(Integer num, int offset, int limit) throws Exception;
	// 디자이너 갤러리 리스트(디자이너)
	List<DesGallery> desGalleryListDesignerPage(Integer num, int offset, int limit) throws Exception;
}
