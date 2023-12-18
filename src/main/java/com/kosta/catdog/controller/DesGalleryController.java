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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.kosta.catdog.entity.DesGallery;
import com.kosta.catdog.entity.DesGalleryLike;
import com.kosta.catdog.entity.Designer;
import com.kosta.catdog.entity.UserGalleryComment;
import com.kosta.catdog.repository.DesGalleryLikeRepository;
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
	@Autowired DesGalleryLikeRepository desGalleryLikeRepository;
	
	
	
	
	@GetMapping("/desgalview/{num}")
	public void ImageView(@PathVariable Integer num, HttpServletResponse response) {
		try {
			desGalleryService.fileView(num, response.getOutputStream());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	@PostMapping("/desgalleryreg")
	public ResponseEntity<DesGallery> registerDesGallery(
			@RequestParam("content") String content,
			@RequestPart(value="file", required = false) MultipartFile file,
			@RequestParam("desId") String desId) {
		try {
			DesGallery desGallery = new DesGallery();
			desGallery.setContent(content);
			desGallery.setDesId(desId);
			desGallery.setLikeCnt(0);
			
			desGalleryService.registerDesGallery(desGallery,file);
			
			return new ResponseEntity<DesGallery>(desGallery, HttpStatus.OK);
		} catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<DesGallery>(HttpStatus.BAD_REQUEST);	
		}
	}
	
	@PostMapping("/modidesgallery")
	public ResponseEntity<DesGallery> modifyDesGallery(
			@RequestParam("content") String content,
			@RequestPart(value="file", required = false) MultipartFile file,
			@RequestParam("desId") String desId) {
		try {
			DesGallery desGallery = new DesGallery();
			desGallery.setContent(content);
			desGallery.setDesId(desId);
			
			desGalleryService.modifyDesGallery(desGallery, file);
			return new ResponseEntity<DesGallery>(HttpStatus.OK);
		} catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<DesGallery>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@DeleteMapping("/desgallery/{num}")
	public ResponseEntity<Integer> deleteDesGallery(@PathVariable Integer num) {
		try {
			desGalleryService.deleteDesGallery(num);
			return new ResponseEntity<Integer>(num, HttpStatus.OK);
		} catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Integer>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/desgallerydetail")
	public ResponseEntity<Object> findDesGallery(@RequestParam("galnum") Integer galNum,@RequestParam("usernum") Integer userNum) {
		try {
			DesGallery desGallery = desGalleryService.findDesGallery(galNum);
			Designer des = userDslRepository.FindDesignerById(desGallery.getDesId());
			Boolean isLike = userDslRepository.FindIsDesGalLike(galNum,userNum);
			
	
			
			 
			Map<String, Object> response = new HashMap<>();
		        response.put("desGallery", desGallery);
		        response.put("designer", des);
		        response.put("isLike", isLike);
		        
		        
			
			return new ResponseEntity<Object>(response, HttpStatus.OK);
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
			PageRequest pageRequest = PageRequest.of(page, size, sort);
			return desGalleryRepository.findAll(pageRequest);
		} catch(Exception e) {
			e.printStackTrace();
		}
		return null;		
	}	
	
	
	
	// 디자이너 갤러리 리스트(샵 페이지, 한번에 9개씩 호출)
	@GetMapping("/desgalleryshop")
	public ResponseEntity<List<DesGallery>>  desGalleryListShopPage(@RequestParam("num") String num,
			@RequestParam("offset") Integer offset, @RequestParam("limit") Integer limit) {
		try {
			Integer inum = Integer.parseInt(num);
			List<DesGallery> desGalleryList = userDslRepository.findDesGalleryListShopPage(inum, offset, limit);
			return new ResponseEntity<List<DesGallery>> (desGalleryList,HttpStatus.OK);
		} catch(Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<List<DesGallery>> (HttpStatus.BAD_REQUEST);
	}
		
	// 디자이너 갤러리 리스트(디자이너 페이지, 한번에 9개씩 호출)
	@GetMapping("/desgallerydesigner")
	public List<DesGallery> desGalleryListDesignerPage(@RequestParam("num") Integer num,
			@RequestParam("offset") int offset, @RequestParam("limit") int limit) {
		try {
			List<DesGallery> DesGalleryList = userDslRepository.findDesGalleryListDesignerPage(num, offset, limit);
			return DesGalleryList;
		} catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@PostMapping("/desgallerylike")
	public ResponseEntity<Boolean> LikeDesGallery(@RequestParam ("galNum") Integer galNum , @RequestParam ("userNum") Integer userNum ){
		
		try {
			
		DesGalleryLike desGalleryLike = new DesGalleryLike();
		desGalleryLike.setDesGalNum(galNum);
		desGalleryLike.setUserNum(userNum);
		
		DesGallery desGal = desGalleryRepository.findById(galNum).get();
		
		
		if(userDslRepository.FindDesGalLike(galNum, userNum)==null) {
			desGalleryLikeRepository.save(desGalleryLike);
			desGal.setLikeCnt(desGal.getLikeCnt()+1);
			desGalleryRepository.save(desGal);
			return new ResponseEntity<Boolean> (true,HttpStatus.OK);
			}
		else {
			Integer num = userDslRepository.FindDesGalLike(galNum, userNum).getNum();
			desGalleryLikeRepository.deleteById(num);
			desGal.setLikeCnt(desGal.getLikeCnt()-1);
			desGalleryRepository.save(desGal);
			return new ResponseEntity<Boolean> (false,HttpStatus.OK);
			
		}
			
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Boolean> (HttpStatus.BAD_REQUEST);
		}
		
	}
	
	
	
	
}