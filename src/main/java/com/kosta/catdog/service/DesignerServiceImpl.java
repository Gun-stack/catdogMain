package com.kosta.catdog.service;

import com.kosta.catdog.entity.DesFileVo;
import com.kosta.catdog.entity.ShopFileVO;
import com.kosta.catdog.repository.DesFileVORepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kosta.catdog.entity.DesGallery;
import com.kosta.catdog.entity.Designer;
import com.kosta.catdog.repository.DesignerRepository;
import com.kosta.catdog.repository.UserDslRepository;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@Service
public class DesignerServiceImpl implements DesignerService {
	
	@Autowired
	private UserDslRepository userDslRepository;
	@Autowired
	private DesignerRepository designerRepository;

	@Autowired
	private DesFileVORepository desFileVORepository;

	@Override
	public void addDesignerInfo(Designer designer) throws Exception {
		designerRepository.save(designer);
	}
		
	@Override
	public Double avgStarCountByDesigner(Integer num) throws Exception {
		return userDslRepository.findAvgStarCountByDesigner(num);
	}

	@Override
	public Designer desreg(Designer des, List<MultipartFile> files) throws Exception {
		String dir = "/Users/baghaengbog/Desktop/Study/upload/des";
		if(files!=null && files.size() !=0 ) {
			String fileNums = "";
			for (MultipartFile file : files) {

				Date today = Date.valueOf(LocalDate.now());


				DesFileVo fileVO = new DesFileVo();
				fileVO.setDir(dir);
				fileVO.setName(file.getOriginalFilename());
				fileVO.setSize(file.getSize());
				fileVO.setType(file.getContentType());
				fileVO.setDate(today);
				//fileVO.setData(file.getBytes());
				desFileVORepository.save(fileVO);

				File uploadFile = new File(dir + fileVO.getNum());
				file.transferTo(uploadFile);
				if (!fileNums.equals(""))
					fileNums += ",";
				fileNums += fileVO.getNum();
			}
			des.setProfImg(fileNums);
		}
		designerRepository.save(des);
		return des;
	}

}
