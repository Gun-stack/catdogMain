package com.kosta.catdog.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kosta.catdog.entity.DesGallery;
import com.kosta.catdog.repository.DesGalleryRepository;
import com.kosta.catdog.repository.UserDslRepository;
import com.kosta.catdog.service.DesGalleryService;

@RestController
public class DesGalleryController {
	
	@Autowired
	private UserDslRepository userDslRepository;
	@Autowired
	private DesGalleryRepository desGalleryRepository;
	@Autowired
	private DesGalleryService desGalleryService;
	
//	@PostMapping("/desgalleryreg")
//	public ResponseEntity<> registerDesGallery(@RequestParam DesGallery desGallery) {
//		try {
//			desGalleryService.registerDesGallery(desGallery);
//			return new ResponseEntity<>(, HttpStatus.OK);
//		} catch(Exception e) {
//			e.printStackTrace();
//			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//		}
//	}
	
//	@PostMapping("/modidesgallery")
//	public ResponseEntity<> modifyDesGallery(@RequestParam DesGallery desGallery) {
//		try {
//			desGalleryService.modifyDesGallery(desGallery);
//			return new ResponseEntity<>(, HttpStatus.OK);
//		} catch(Exception e) {
//			e.printStackTrace();
//			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//		}
//	}
	
//	@DeleteMapping("/")
//	public void deleteDesGallery(@RequestParam Integer num) {
//		try {
//			desGalleryService.deleteDesGallery(num);
//		} catch(Exception e) {
//			e.printStackTrace();
//		}
//	}
	
	@GetMapping("/desgallery/{num}")
	public ResponseEntity<Object> findDesGallery(@RequestParam("num") Integer num) {
		try {
			DesGallery desGallery = desGalleryService.findDesGallery(num);
			return new ResponseEntity<Object>(desGallery, HttpStatus.OK);
		} catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
		}
	}

	// 디자이너 갤러리 리스트(메인화면 -> 갤러리 구경하기, 한번에 9개씩 호출)
	@GetMapping("/desgallery")
	public Slice<DesGallery> desGalleryListMainPage(@RequestParam("page") Integer page,
			@RequestParam("size") Integer size) {
		try {
			Sort sort = Sort.by("date").descending();
			PageRequest pageRequest = PageRequest.of(0, 9, sort);
			return desGalleryRepository.findAll(pageRequest);
		} catch(Exception e) {
			e.printStackTrace();
		}
		return null;		
	}	
	
	// 디자이너 갤러리 리스트(샵 페이지, 한번에 9개씩 호출)
	@GetMapping("/desgalleryshop")
	public List<DesGallery> desGalleryListShopPage(@RequestParam("num") Integer num,
			@RequestParam("offset") int offset, @RequestParam("limit") int limit) {
		try {
			List<DesGallery> DesGalleryList = userDslRepository.findDesGalleryListShopPage(num, 0, 9);
			return DesGalleryList;
		} catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
		
	// 디자이너 갤러리 리스트(디자이너 페이지, 한번에 9개씩 호출)
	@GetMapping("/desgallerydesigner")
	public List<DesGallery> desGalleryListDesignerPage(@RequestParam("num") Integer num,
			@RequestParam("offset") int offset, @RequestParam("limit") int limit) {
		try {
			List<DesGallery> DesGalleryList = userDslRepository.findDesGalleryListDesignerPage(num, 0, 9);
			return DesGalleryList;
		} catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}