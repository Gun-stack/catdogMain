package com.kosta.catdog.controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.kosta.catdog.entity.*;
import com.kosta.catdog.service.ShopService;
import com.kosta.catdog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;

@RestController
public class ShopController {

	@Autowired
	private ShopService shopService;
	@Autowired
	private UserService userService;
	
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
	public ResponseEntity<Boolean> shopreg(@RequestPart(value="file", required = false) List<MultipartFile> file,
						@RequestParam("userId") String id,
					   @RequestParam("sId") Integer sId,
						@RequestParam("name") String name,
						@RequestParam("address_road") String address_road,
						@RequestParam("address_detail") String address_detail,
						@RequestParam("latitude") BigDecimal latitude,
						@RequestParam("longitude") BigDecimal longitude
						) {
		// 사업자 번호 입력 받기


		try{
		Shop shop = new Shop();
		shop.setName(name);
		shop.setSId(sId);
		shop.setAddressRoad(address_road);
		shop.setAddressDetail(address_detail);
		shop.setLat(latitude);
		shop.setLon(longitude);


		if (file != null && !file.isEmpty()) {
			shopService.addShop(shop, file);
		} else {
			shopService.addShop(shop, null);  // 또는 파일을 처리하지 않는 메서드 호출
		}
			User user = userService.getUserInfoById(id);
			user.setRoles("ROLE_SHOP");
			userService.modifyRole(id);
			return new ResponseEntity<Boolean>(true, HttpStatus.OK);

		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Boolean>(false,HttpStatus.BAD_REQUEST);
		}


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
