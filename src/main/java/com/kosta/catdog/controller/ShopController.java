package com.kosta.catdog.controller;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.kosta.catdog.entity.Designer;
import com.kosta.catdog.entity.Reservation;
import com.kosta.catdog.entity.Review;
import com.kosta.catdog.entity.Shop;
import com.kosta.catdog.entity.User;
import com.kosta.catdog.repository.DesignerRepository;
import com.kosta.catdog.repository.ShopRepository;
import com.kosta.catdog.repository.UserDslRepository;
import com.kosta.catdog.service.ShopService;
import com.kosta.catdog.service.UserService;


@RestController
public class ShopController {

	@Autowired
	private ShopService shopService;
	@Autowired
	private UserService userService;
	@Autowired
	private UserDslRepository userDslRepository;
	@Autowired 
	private ShopRepository shopRepository;
	@Autowired
	private DesignerRepository designerRepository;
	
	
	//샵사진조회

		@GetMapping("/shopimg/{num}")
		public void imageView(@PathVariable Integer num, HttpServletResponse response) {
			System.out.println("ShopImg/Num!!!");
			try {
				shopService.fileView(num, response.getOutputStream());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	
	
	
	//샵이미지 등록
		@PostMapping("/regshopbgimg")
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
	
	@GetMapping("/shoplistall")
	public ResponseEntity<List<Shop>> listSHop() 
	{
		try {
			List<Shop> shopList =  shopRepository.findAll();
			return new ResponseEntity<List<Shop>>(shopList,HttpStatus.OK);
			
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<List<Shop>>(HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("/shopinfobynum")
	public ResponseEntity<Shop> shopInfoByNum(@RequestParam Integer num) 
	{
		try {
			Shop shop =  shopRepository.findById(num).get();
			return new ResponseEntity<Shop>(shop,HttpStatus.OK);
			
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Shop>(HttpStatus.NOT_FOUND);
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
	@PostMapping("/shopreg")
	public ResponseEntity<Boolean> shopreg(@RequestPart(value="file", required = false) List<MultipartFile> file,
						@RequestParam("userId") String id,
					   @RequestParam("sId") String sId,
						@RequestParam("name") String name,
						@RequestParam("address_road") String address_road,
						@RequestParam("address_detail") String address_detail,
						@RequestParam("latitude") BigDecimal latitude,
						@RequestParam("longitude") BigDecimal longitude
						) {
		// 사업자 번호 입력 받기


		try{
		Shop shop = new Shop();
		shop.setId(id);
		shop.setName(name);
		shop.setSId(sId);
		shop.setAddressRoad(address_road);
		shop.setAddressDetail(address_detail);
		shop.setLat(latitude);
		shop.setLon(longitude);
		shop.setId(id);


		if (file != null && !file.isEmpty()) {
			shopService.addShop(shop, file);
		} else {
			shopService.addShop(shop, null);  // 또는 파일을 처리하지 않는 메서드 호출
		}
			User user = userService.getUserInfoById(id);
			user.setRoles("ROLE_SHOP");
			userService.modifyRole(id);
			Designer des = userDslRepository.FindDesignerById(id);
			des.setSId(sId);
			designerRepository.save(des);
			
			return new ResponseEntity<Boolean>(true, HttpStatus.OK);

		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Boolean>(false,HttpStatus.BAD_REQUEST);
		}


	}

	// 샵 정보 수정
	@PostMapping("/shopmodi")
	public ResponseEntity<String> shopmodi(@RequestPart(value="file", required = false) List<MultipartFile> file,
										   @RequestParam("shopnum") Integer num,
										   @RequestParam("sId") String sId,
										   @RequestParam("name") String name,
										   @RequestParam("address_road") String address_road,
										   @RequestParam("address_detail") String address_detail,
										   @RequestParam("latitude") BigDecimal latitude,
										   @RequestParam("longitude") BigDecimal longitude
	) {

		System.out.println("Shop Modi !!!");
		String res ;

		try{
			Shop shop = new Shop();
			shop = shopService.selectshop(num);
			String bid = shop.getSId();

			shop.setSId(sId);
			shop.setName(name);
			shop.setAddressRoad(address_road);
			shop.setAddressDetail(address_detail);
			shop.setLat(latitude);
			shop.setLon(longitude);

			String aid = shop.getSId();

			System.out.println("File : " + file);



			if (file != null && !file.isEmpty()) {
				res = shopService.modiShop(shop, file);
			} else {
				res = shopService.modiShop(shop, null);  // 또는 파일을 처리하지 않는 메서드 호출
			}
			Designer des = userDslRepository.FindDesignerById(shop.getId());
			des.setSId(aid);
			designerRepository.save(des);

			return new ResponseEntity<String>( res,HttpStatus.OK);

		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>("false",HttpStatus.BAD_REQUEST);
		}


	}

	
	// 메뉴 등록 
	public void regshopmenu(String menu) {
		System.out.println("regShopMenu !!");
	}


	// 샵 조회
	@GetMapping("/shoplist")
	public List<Shop> shoplist(String id){
		System.out.println("Shop List !!!");
		System.out.println("Id : " + id);
		try{
			List<Shop> sl =  shopService.listshop(id);
			System.out.println("Shop Info : " + sl);
			return sl != null ? sl : Collections.emptyList();
		}catch (Exception e){
			e.printStackTrace();
			return Collections.emptyList();
		}
	}

	@PostMapping("/shopdesreg")
	public void shopdesreg(@RequestBody Designer des) {
		System.out.println("shopdesreg!!!");
			try{
				System.out.println("Des : " + des);
				shopService.desreg(des);
			} catch(Exception e) {
				e.printStackTrace();
		}

	}

}
