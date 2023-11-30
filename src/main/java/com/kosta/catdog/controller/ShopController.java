package com.kosta.catdog.controller;

import java.util.List;

import org.springframework.web.bind.annotation.RestController;

import com.kosta.catdog.entity.Designer;
import com.kosta.catdog.entity.Reservation;
import com.kosta.catdog.entity.Review;
import com.kosta.catdog.entity.Shop;

@RestController
public class ShopController {
	
	// 샵 조회(인기순)
	public List<Shop> listshopbystar(){
		System.out.println("listShopByStar!! ");
		return null;
	}
	// 샵 조회(거리순)
	public List<Shop> listshopbydis(){
		System.out.println("listShopByDis!! ");
		return null;
	}
	// 샵 정보(홈)
	public Shop selshoph(Integer num) {
		System.out.println("selShoph !!");
		return null;
	}
	// 샵 정보(메뉴)
	public Shop selshopm(Integer num) {
		System.out.println("selShopm !!");
		return null;
	}
	// 샵 정보(디자이너)
	public List<Designer> selshopd(Integer num){
		System.out.println("selShopD !!");
		return null;
	}
	// 샵 정보(스타일)
	public Shop selshops(Integer num){
		System.out.println("selShopS !!");
		return null;
	}
	// 샵 정보(예약)
	public List<Reservation> selshopres(Integer num){
		System.out.println("selshopRes !!");
		return null;
	}
	// 샵 정보(리뷰)
	public List<Review> selshoprev(Integer num){
		System.out.println("selShopRev !!");
		return null;
	}
	// 샵 정보(문의)
	public Shop selshopt(Integer num) {
		System.out.println("selShopText !!");
		return null;
	}
	// 샵 등록
	public void shopreg(Shop shop) {
		System.out.println("shopReg !!");
	}
	// 공지사항 등록
	public void regshopnotice(String notice) {
		System.out.println("regShopNotice !!");	
	}
	// 메뉴 등록 
	public void regshopmenu(String menu) {
		System.out.println("regShopMenu !!");
	}

}
