package com.kosta.catdog.service;

import java.io.OutputStream;
import java.util.List;

import com.kosta.catdog.entity.Designer;
import com.kosta.catdog.entity.Pet;
import com.kosta.catdog.entity.Review;
import com.kosta.catdog.entity.Shop;
import org.springframework.web.multipart.MultipartFile;

public interface ShopService {
	// 샵 등록
	Shop addShop(Shop shop , List<MultipartFile> files) throws Exception;
	//샵 수정
	String modiShop(Shop shop, List<MultipartFile> files) throws Exception;
	// 디자이너 삭제
	void deleteDesigner() throws Exception;
	// 샵 기본정보 등록
	void addShopInfo(Shop shop) throws Exception;
	// 공지사항 등록
	void addShopNotice(String notice) throws Exception;
	// 소속 디자이너 모아 보여주기
	List<Designer> designerListByShop(Integer num) throws Exception;

	// 샵 목록 조회(id로 등록한 샵)
	List<Shop> listshop(String id) throws Exception;

	// 샵 조회(샵 번호로 조회)
	Shop selectshop(Integer num) throws Exception;

	// 샵 이미지 조회
	void fileView(Integer num, OutputStream out) throws Exception ;
	
	Shop addShopImg(Shop shop, MultipartFile file) throws Exception;

	void desreg(Designer des) throws Exception;
}
