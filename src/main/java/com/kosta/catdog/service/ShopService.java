package com.kosta.catdog.service;

import java.util.List;

import com.kosta.catdog.entity.Designer;
import com.kosta.catdog.entity.Review;
import com.kosta.catdog.entity.Shop;

public interface ShopService {
	// 별점 등록
	void plusStarCount(Integer star) throws Exception;
	// 샵 등록
	void addShop(Shop shop) throws Exception;
	// 디자이너 등록
	void addDesigner(Designer designer) throws Exception;
	// 샵 정보 등록
	void addShopInfo(Shop shop) throws Exception;
	// 공지사항 등록
	void addShopNotice(String notice) throws Exception;
	// 소속 디자이너 모아 보여주기
	List<Designer> designerListByShop(Integer num) throws Exception;
	// 고객 리뷰 모아 보여주기
	List<Review> reviewListByShop(Integer num) throws Exception;
}
