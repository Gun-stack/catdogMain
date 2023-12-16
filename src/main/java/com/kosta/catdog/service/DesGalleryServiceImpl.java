package com.kosta.catdog.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import com.kosta.catdog.entity.DesGallery;
import com.kosta.catdog.repository.DesGalleryRepository;
import com.kosta.catdog.repository.UserDslRepository;
@Service
public class DesGalleryServiceImpl implements DesGalleryService {

	@Autowired
	private UserDslRepository userDslRepository;
	@Autowired
	private DesGalleryRepository desGalleryRepository;
	
	@Value("${desgallery.upload.dir}")
	private String uploadDir; //파일이 업로드 되는dir
	
	@Override
	public DesGallery registerDesGallery(DesGallery desGallery ,MultipartFile file) throws Exception {
		String fileNums="";
		Date today = Date.valueOf(LocalDate.now());
		desGallery.setDir(uploadDir);
		desGallery.setName(file.getOriginalFilename());
		desGallery.setSize(file.getSize());
		desGallery.setType(file.getContentType());
		desGallery.setDate(today);
		desGalleryRepository.save(desGallery);
		File uploadFile= new File(uploadDir+desGallery.getNum());
		file.transferTo(uploadFile);
		
		String num = String.valueOf(desGallery.getNum()) ;
		desGallery.setImg(num);
		desGalleryRepository.save(desGallery);
		return desGallery;
	}
	
	@Override
	public void fileView(Integer num, OutputStream out) throws Exception {
		try {
		 DesGallery desGallery = desGalleryRepository.findById(num).get();
		 String fileNum = desGallery.getImg();
//		FileCopyUtils.copy(fileVo.getData(), out); //데이타 뿌려주기
		FileInputStream fis = new FileInputStream(desGallery.getDir()+fileNum);//폴더에서 가져오기 
		FileCopyUtils.copy(fis, out);
		out.flush();
	} catch (Exception e) {
		e.printStackTrace();
	}	
}

	@Override
	public DesGallery modifyDesGallery(DesGallery desGallery, MultipartFile file) throws Exception {
		desGalleryRepository.save(desGallery);
		return desGallery;
	}

	@Override
	public void deleteDesGallery(Integer num) throws Exception {
		desGalleryRepository.deleteById(num);
	}

	@Override
	public DesGallery findDesGallery(Integer num) throws Exception {
		return userDslRepository.findDesGallery(num);
	}

	@Override
	public Slice<DesGallery> desGalleryListMainPage(Pageable pageable) throws Exception {
		return desGalleryRepository.findAll(pageable);
	}

	@Override
	public List<DesGallery> desGalleryListShopPage(Integer num, int offset, int limit) throws Exception {
		return userDslRepository.findDesGalleryListShopPage(num, offset, limit);
	}

	@Override
	public List<DesGallery> desGalleryListDesignerPage(Integer num, int offset, int limit) throws Exception {
		return userDslRepository.findDesGalleryListDesignerPage(num, offset, limit);
	}
}
