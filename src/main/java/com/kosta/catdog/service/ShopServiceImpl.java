package com.kosta.catdog.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


import com.kosta.catdog.entity.*;
import com.kosta.catdog.repository.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import com.kosta.catdog.entity.Designer;
import com.kosta.catdog.entity.ReviewFileVO;
import com.kosta.catdog.entity.Shop;
import com.kosta.catdog.entity.ShopFileVO;
import com.kosta.catdog.repository.ShopFileVORepository;

import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;


@Service
public class ShopServiceImpl implements ShopService {
	
	@Autowired
	private UserDslRepository userDslRepository;
	@Autowired
	private DesignerDslRepository designerDslRepository;
	@Autowired
	private DesignerRepository designerRepository;
	@Autowired
	private ShopRepository shopRepository;
	@Autowired
	private ShopDslRepository shopDslRepository;

	@Autowired
	private ShopFileVORepository shopFileVORepository;

	@Value("${shop.upload.dir}")
	private String uploadDir; //파일이 업로드 되는dir
		
	@Override
	public Shop addShop(Shop shop , List<MultipartFile> files) throws Exception {

		if(files!=null && files.size() !=0 ) {
			String fileNums = "";
			for (MultipartFile file : files) {

				Date today = Date.valueOf(LocalDate.now());


				ShopFileVO fileVO = new ShopFileVO();
				fileVO.setDir(uploadDir);
				fileVO.setName(file.getOriginalFilename());
				fileVO.setSize(file.getSize());
				fileVO.setType(file.getContentType());
				fileVO.setDate(today);
				//fileVO.setData(file.getBytes());
				shopFileVORepository.save(fileVO);

				File uploadFile = new File(uploadDir + fileVO.getNum());
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
	public String modiShop(Shop shop, List<MultipartFile> files) throws Exception {

		Shop newShop = shopRepository.findById(shop.getNum()).get();
		if(files!=null && files.size() !=0 ) {
			String fileNums = "";
			for (MultipartFile file : files) {

				Date today = Date.valueOf(LocalDate.now());


				ShopFileVO fileVO = new ShopFileVO();
				fileVO.setDir(uploadDir);
				fileVO.setName(file.getOriginalFilename());
				fileVO.setSize(file.getSize());
				fileVO.setType(file.getContentType());
				fileVO.setDate(today);
				//fileVO.setData(file.getBytes());
				shopFileVORepository.save(fileVO);

				File updateFile = new File(uploadDir + fileVO.getNum());
				file.transferTo(updateFile);
//				if (!fileNums.equals(""))
//					fileNums += ",";
				fileNums = String.valueOf(fileVO.getNum());
			}
			shop.setProfImg(fileNums);
		}
		else if(files == null){
			shop.setProfImg(newShop.getProfImg());
		}
		shop.setNum(newShop.getNum());
		shopRepository.save(shop);
		System.out.println("SHOP INFO : " + shop);
		return "true";
	}

	@Override
	public Shop addShopImg(Shop shop, MultipartFile file) throws Exception {

		if(file !=null) {
		String fileNums="";
		
		Date today = Date.valueOf(LocalDate.now());
		
		ShopFileVO fileVO = new ShopFileVO();
		
		fileVO.setDir(uploadDir);
		fileVO.setName(file.getOriginalFilename());
		fileVO.setSize(file.getSize());
		fileVO.setType(file.getContentType());
		fileVO.setDate(today);
		shopFileVORepository.save(fileVO);
		
		File uploadFile= new File(uploadDir+fileVO.getNum());
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

	@Override
	public void desreg(Designer des) throws Exception {
		designerDslRepository.modifyDes(des);
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
	public void deleteDesigner(Integer num, String sId) throws Exception {
		designerDslRepository.deleteDesigner(num, sId);
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

	@Override
	public Shop selectshop(Integer num) throws Exception {
		return shopDslRepository.fidnByNum(num);
	}

//	@Override
//	public void fileView(Integer num, OutputStream out) throws Exception {
//		try {
////			Optional<PetFileVO> fileVoOptional  = petFileVORepository.findById(num);
////			PetFileVO fileVo = fileVoOptional.get();
//
////			FileCopyUtils.copy(fileVo.getData(), out); //데이타 뿌려주기
////			FileInputStream fis = new FileInputStream(fileVo.getDir()+num);//폴더에서 가져오기
//
//			String dir = "/Users/baghaengbog/Desktop/Study/upload/shop";
//			FileInputStream fis = new FileInputStream(dir+num);
//			FileCopyUtils.copy(fis, out);
//			out.flush();
//
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}

}
