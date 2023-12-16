package com.kosta.catdog.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.kosta.catdog.entity.User;
import com.kosta.catdog.entity.UserGallery;
import com.kosta.catdog.repository.UserDslRepository;
import com.kosta.catdog.repository.UserGalleryRepository;
import com.kosta.catdog.service.UserGalleryService;

@RestController
public class UserGalleryController {
	
	@Autowired
	private UserDslRepository userDslRepository;
	@Autowired
	private  UserGalleryRepository userGalleryRepository;
	@Autowired
	private UserGalleryService userGalleryService;	
	
	@GetMapping("/usergalview/{num}")
	public void ImageView(@PathVariable Integer num, HttpServletResponse response) {
		try {
			userGalleryService.fileView(num, response.getOutputStream());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	@PostMapping("/usergalleryreg")
	public ResponseEntity<UserGallery> registerUserGallery(
			@RequestParam("content") String content,
			@RequestPart(value="file", required = false) MultipartFile file,
			@RequestParam("userId") String UserId) {
		try {
			UserGallery userGallery = new UserGallery();
			userGallery.setContent(content);
			userGallery.setUserId(UserId);
			userGallery.setLikeCnt(0);
			userGallery.setCommentCnt(0);
			userGalleryService.registerUserGallery(userGallery,file);
			return new ResponseEntity<UserGallery>(userGallery, HttpStatus.OK);
		} catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<UserGallery>(HttpStatus.BAD_REQUEST);	
		}
	}
	
//	@PostMapping("/modiUsergallery")
//	public ResponseEntity<> modifyUserGallery(@RequestParam UserGallery UserGallery) {
//		try {
//			UserGalleryService.modifyUserGallery(UserGallery);
//			return new ResponseEntity<>(, HttpStatus.OK);
//		} catch(Exception e) {
//			e.printStackTrace();
//			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//		}
//	}
	
//	@DeleteMapping("/")
//	public void deleteUserGallery(@RequestParam Integer num) {
//		try {
//			UserGalleryService.deleteUserGallery(num);
//		} catch(Exception e) {
//			e.printStackTrace();
//		}
//	}
	
	@GetMapping("/usergallerydetail")
	public ResponseEntity<Object> findUserGallery(@RequestParam("num") Integer num) {
		try {
			UserGallery userGallery = userGalleryService.findUserGallery(num);
			User User = userDslRepository.findById(userGallery.getUserId());
			Map<String, Object> response = new HashMap<>();
		        response.put("userGallery", userGallery);
		        response.put("user", User);
			
			return new ResponseEntity<Object>(response, HttpStatus.OK);
		} catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
		}
	}

	// 디자이너 갤러리 리스트(메인화면 -> 갤러리 구경하기, 한번에 9개씩 호출)
	@GetMapping("/usergallery")
	public Slice<UserGallery> UserGalleryListMainPage(@RequestParam("page") Integer page,
			@RequestParam("size") Integer size) {
		try {
			Sort sort = Sort.by("date").descending();
			PageRequest pageRequest = PageRequest.of(page, size, sort);
			return userGalleryRepository.findAll(pageRequest);
		} catch(Exception e) {
			e.printStackTrace();
		}
		return null;		
	}	
	
	
	
	// 디자이너 갤러리 리스트(샵 페이지, 한번에 9개씩 호출)
//	@GetMapping("/usergalleryshop")
//	public ResponseEntity<List<UserGallery>>  UserGalleryListShopPage(@RequestParam("num") String num,
//			@RequestParam("offset") Integer offset, @RequestParam("limit") Integer limit) {
//		try {
//			Integer inum = Integer.parseInt(num);
//			List<UserGallery> userGalleryList = userDslRepository.findUserGalleryListShopPage(inum, offset, limit);
//			return new ResponseEntity<List<UserGallery>> (userGalleryList,HttpStatus.OK);
//		} catch(Exception e) {
//			e.printStackTrace();
//		}
//		return new ResponseEntity<List<UserGallery>> (HttpStatus.BAD_REQUEST);
//	}
//		
	// 디자이너 갤러리 리스트(디자이너 페이지, 한번에 9개씩 호출)
//	@GetMapping("/UsergalleryUser")
//	public List<UserGallery> UserGalleryListUserignerPage(@RequestParam("num") Integer num,
//			@RequestParam("offset") int offset, @RequestParam("limit") int limit) {
//		try {
//			List<UserGallery> UserGalleryList = userDslRepository.findUserGalleryListUserPage(num, 0, 9);
//			return UserGalleryList;
//		} catch(Exception e) {
//			e.printStackTrace();
//		}
//		return null;
//	}
}