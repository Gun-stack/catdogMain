package com.kosta.catdog.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import com.kosta.catdog.entity.*;
import com.kosta.catdog.repository.ShopDslRepository;
import com.kosta.catdog.repository.ShopFileVORepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import com.kosta.catdog.entity.Designer;
import com.kosta.catdog.entity.ReviewFileVO;
import com.kosta.catdog.entity.Shop;
import com.kosta.catdog.entity.ShopFileVO;
import com.kosta.catdog.repository.ShopFileVORepository;
import com.kosta.catdog.repository.ShopRepository;
import com.kosta.catdog.repository.UserDslRepository;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ShopServiceImpl implements ShopService {
	
	@Autowired
	private UserDslRepository userDslRepository;
	@Autowired
	private ShopRepository shopRepository;
	@Autowired
	private ShopDslRepository shopDslRepository;

	@Autowired
	private ShopFileVORepository shopFileVORepository;

	@Override
	public Shop addShop(Shop shop , List<MultipartFile> files) throws Exception {
		String dir = "c:/kkw/upload/shop/";
		if(files!=null && files.size() !=0 ) {
			String fileNums = "";
			for (MultipartFile file : files) {

				Date today = Date.valueOf(LocalDate.now());


				ShopFileVO fileVO = new ShopFileVO();
				fileVO.setDir(dir);
				fileVO.setName(file.getOriginalFilename());
				fileVO.setSize(file.getSize());
				fileVO.setType(file.getContentType());
				fileVO.setDate(today);
				//fileVO.setData(file.getBytes());
				shopFileVORepository.save(fileVO);

				File uploadFile = new File(dir + fileVO.getNum());
				file.transferTo(uploadFile);
				if (!fileNums.equals(""))
					fileNums += ",";
				fileNums += fileVO.getNum();
			}
			shop.setProfImg(fileNums);
		}
		shopRepository.save(shop);
		return shop;
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
		shopFileVORepository.save(fileVO);
		
		File uploadFile= new File(dir+fileVO.getNum());
		file.transferTo(uploadFile);
		if(!fileNums.equals(""))
			fileNums += ",";
		fileNums += fileVO.getNum();
		
		shop.setBgImg(fileNums);
		}
		//리뷰저장
		shopRepository.save(shop);
		return shop;
	}
	
	// 사진보기
	@Override
	public void fileView(Integer num, OutputStream out) throws Exception {
try {
			Integer fileNum =num;
			Optional<ShopFileVO> fileVoOptional  = shopFileVORepository.findById(fileNum);
			ShopFileVO fileVo = fileVoOptional.get();
//			FileCopyUtils.copy(fileVo.getData(), out); //데이타 뿌려주기
			FileInputStream fis = new FileInputStream(fileVo.getDir()+fileNum);//폴더에서 가져오기 
			FileCopyUtils.copy(fis, out);
			out.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
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
	public List<Shop> listshop(String id) throws Exception {
		return shopDslRepository.findById(id);
	}

// 	@Override
// 	public void fileView(Integer num, OutputStream out) throws Exception {
// 		try {
// //			Optional<PetFileVO> fileVoOptional  = petFileVORepository.findById(num);
// //			PetFileVO fileVo = fileVoOptional.get();

// //			FileCopyUtils.copy(fileVo.getData(), out); //데이타 뿌려주기
// //			FileInputStream fis = new FileInputStream(fileVo.getDir()+num);//폴더에서 가져오기

// 			String dir = "/Users/baghaengbog/Desktop/Study/upload/shop";
// 			FileInputStream fis = new FileInputStream(dir+num);
// 			FileCopyUtils.copy(fis, out);
// 			out.flush();

// 		} catch (Exception e) {
// 			e.printStackTrace();
// 		}
// 	}

}
