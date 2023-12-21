package com.kosta.catdog.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import com.kosta.catdog.entity.UserGallery;
import com.kosta.catdog.repository.UserDslRepository;
import com.kosta.catdog.repository.UserGalleryRepository;

@Service
public class UserGalleryServiceImpl implements UserGalleryService {

	@Autowired
	private UserDslRepository userDslRepository;
	
	@Autowired
	private UserGalleryRepository userGalleryRepository;
	
	@Override
	public void registerUserGallery(UserGallery userGallery ,MultipartFile file) throws Exception {
		String dir = "c:/kkw/upload/usergallery/";
		String fileNums="";
		Timestamp day = Timestamp.valueOf(LocalDateTime.now());
		userGallery.setDir(dir);
		userGallery.setName(file.getOriginalFilename());
		userGallery.setSize(file.getSize());
		userGallery.setType(file.getContentType());
		userGallery.setDate(day);
		userGalleryRepository.save(userGallery);
		File uploadFile= new File(dir+userGallery.getNum());
		file.transferTo(uploadFile);
		
		String num = String.valueOf(userGallery.getNum()) ;
		userGallery.setImg(num);
		userGalleryRepository.save(userGallery);
	}
	
	@Override
	public void fileView(Integer num, OutputStream out) throws Exception {
		try {
		 UserGallery userGallery = userGalleryRepository.findById(num).get();
		 String fileNum = userGallery.getImg();
//		FileCopyUtils.copy(fileVo.getData(), out); //데이타 뿌려주기
		FileInputStream fis = new FileInputStream(userGallery.getDir()+fileNum);//폴더에서 가져오기 
		FileCopyUtils.copy(fis, out);
		out.flush();
	} catch (Exception e) {
		e.printStackTrace();
	}
	
}

	@Override
	public void modifyUserGallery(UserGallery userGallery) throws Exception {
		userGalleryRepository.save(userGallery);
	}

	@Override
	public void deleteUserGallery(Integer num) throws Exception {
		userGalleryRepository.deleteById(num);
	}

	@Override
	public UserGallery findUserGallery(Integer num) throws Exception {
		return userDslRepository.findUserGallery(num);
	}

//	@Override
//	public Slice<UserGallery> UserGalleryListMainPage(Pageable pageable) throws Exception {
//		return userGalleryRepository.findAll(pageable);
//	}
//
//	@Override
//	public List<UserGallery> UserGalleryListShopPage(Integer num, int offset, int limit) throws Exception {
//		return userDslRepository.findUserGalleryListShopPage(num, offset, limit);
//	}
//
//	@Override
//	public List<UserGallery> UserGalleryListUserPage(Integer num, int offset, int limit) throws Exception {
//		return userDslRepository.findUserGalleryListUserignerPage(num, offset, limit);
//	}
}
