package com.kosta.catdog.service;

import java.io.File;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.kosta.catdog.entity.Designer;
import com.kosta.catdog.entity.Review;
import com.kosta.catdog.entity.ReviewFileVO;
import com.kosta.catdog.entity.Shop;
import com.kosta.catdog.entity.ShopFileVO;
import com.kosta.catdog.repository.ShopFileVORepository;
import com.kosta.catdog.repository.ShopRepository;
import com.kosta.catdog.repository.UserDslRepository;

@Service
public class ShopServiceImpl implements ShopService {
	
	@Autowired
	private UserDslRepository userDslRepository;
	@Autowired
	private ShopRepository shopRepository;
	@Autowired
	private ShopFileVORepository fileVORepository;
	
	@Override
	public void addShop(Shop shop) throws Exception {
		shopRepository.save(shop);
	}

//	@Override
//	public void addDesigner(String id, String position) throws Exception {
//		userDslRepository.addDesignerToShop(id, position);
//	}
	
	@Override
	public void deleteDesigner() throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addShopInfo(Shop shop) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void addShopNotice(String notice) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Designer> designerListByShop(Integer num) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Review> reviewListByShopOrderByDateDesc(Integer num) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Shop addShopImg(Shop shop, MultipartFile file) throws Exception {
		String dir = "c:/kkw/upload/shop/";
		if(file !=null) {
		String fileNums="";
		Date today = Date.valueOf(LocalDate.now());
		ShopFileVO fileVO = new ShopFileVO();
		fileVO.setDir(dir);
		fileVO.setName(file.getOriginalFilename());
		fileVO.setSize(file.getSize());
		fileVO.setType(file.getContentType());
		fileVO.setDate(today);
		fileVORepository.save(fileVO);
		
		File uploadFile= new File(dir+fileVO.getNum());
		file.transferTo(uploadFile);
		if(!fileNums.equals(""))
			fileNums += ",";
		fileNums += fileVO.getNum();
		
		shop.setProfImg(fileNums);
		}
		//리뷰저장
		shopRepository.save(shop);
		return shop;
	}

}
