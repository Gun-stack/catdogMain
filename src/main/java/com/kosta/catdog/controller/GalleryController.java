package com.kosta.catdog.controller;

import java.util.List;

import org.springframework.web.bind.annotation.RestController;

import com.kosta.catdog.entity.DesGallery;
import com.kosta.catdog.entity.UserGallery;

@RestController
public class GalleryController {

	
	// 갤러리 조회(디자이너)
	public List<DesGallery> selectstgallery(){
		System.out.println("selectStGallery !!");
		return null;
	}
	// 갤러리 조회(유저)
	public List<UserGallery> selectusergallery(){
		System.out.println("selectUserGallery !!");
		return null;
	}
	// 스타일 등록(미용사)
	public void regstgallery(DesGallery dg) {
		System.out.println("regstgallery");
	}
}
