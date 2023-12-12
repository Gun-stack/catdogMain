package com.kosta.catdog.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.kosta.catdog.entity.Designer;
import com.kosta.catdog.entity.Reservation;
import com.kosta.catdog.entity.Review;
import com.kosta.catdog.entity.Shop;
import com.kosta.catdog.repository.ShopRepository;
import com.kosta.catdog.repository.UserDslRepository;
import com.kosta.catdog.service.ShopService;

@RestController
public class ShopController {
	
	@Autowired
	private UserDslRepository userDslRepository;
	@Autowired 
	private ShopRepository shopRepository;
	@Autowired
	private ShopService shopService;
	
	
	//샵이미지 등록
	@PostMapping("/regshopimg")
	public ResponseEntity<Shop> regShopImg(@RequestPart(value="file", required = false) MultipartFile file,
			@RequestParam("shopNum") Integer num	) {
			
		try {
				Shop shopInfo = shopRepository.findById(num).get();
				System.out.println(shopInfo);
				Shop shop = shopService.addShopImg(shopInfo, file);
				
				return new ResponseEntity<Shop>(shop,HttpStatus.OK);
			} catch (Exception e) {
				e.printStackTrace();
				return new ResponseEntity<Shop>(HttpStatus.BAD_REQUEST);
			}
				
	}
	// 공지사항 등록
	@PostMapping("/regshopnotice")
		public ResponseEntity<Shop> regShopNotice(@RequestParam ("notice") String notice,
				@RequestParam("shopNum") Integer num ) {
			try {
				Shop shopInfo = shopRepository.findById(num).get();
				shopInfo.setNotice(notice);
				shopRepository.save(shopInfo);
				return new ResponseEntity<Shop>(shopInfo,HttpStatus.OK);
			} catch (Exception e) {
				e.printStackTrace();
				return new ResponseEntity<Shop>(HttpStatus.BAD_REQUEST);
		}	
	}
	//영업시간 등록
	
	@PostMapping("/regshopworktime")
	public ResponseEntity<Shop> regShopWorktime(@RequestParam ("worktime") String worktime,
			@RequestParam("shopNum") Integer num ) {
		try {
			Shop shopInfo = shopRepository.findById(num).get();
			shopInfo.setWorkTime(worktime);
			shopRepository.save(shopInfo);
			return new ResponseEntity<Shop>(shopInfo,HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Shop>(HttpStatus.BAD_REQUEST);
	}	
}
	
	@PostMapping("/regshopinfo")
	public ResponseEntity<Shop> regShopInfo(@RequestParam ("info") String info,
			@RequestParam("shopNum") Integer num ) {
		try {
			Shop shopInfo = shopRepository.findById(num).get();
			shopInfo.setInfo(info);
			shopRepository.save(shopInfo);
			return new ResponseEntity<Shop>(shopInfo,HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Shop>(HttpStatus.BAD_REQUEST);
	}	
}
	
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
	
	// 메뉴 등록 
	public void regshopmenu(String menu) {
		System.out.println("regShopMenu !!");
	}

}
