package com.kosta.catdog.controller;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.*;

import com.kosta.catdog.entity.Designer;
import com.kosta.catdog.entity.Reservation;
import com.kosta.catdog.entity.Review;
import com.kosta.catdog.entity.Shop;
import org.springframework.web.multipart.MultipartFile;

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
	@PostMapping("/shopreg")
	public void shopreg(@RequestPart(value="file", required = false) List<MultipartFile> file,
						@RequestParam("name") String name,
						@RequestParam("address_road") String address_road,
						@RequestParam("address_detail") String address_detail,
						@RequestParam("latitude") String latitude,
						@RequestParam("longitude") String longitude
						) {
		System.out.println("shopReg !!");
		System.out.println("Shop Name : " + name);
		System.out.println("Shop addressRoad : " + address_road);
		System.out.println("Shop addressDetail : " + address_detail);
		System.out.println("Shop latitude(위도) : " + latitude);
		System.out.println("Shop longitude(경도) : " + longitude);
		System.out.println("Shop profImg : " + file);
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
