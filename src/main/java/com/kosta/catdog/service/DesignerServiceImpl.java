package com.kosta.catdog.service;


import java.io.FileInputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.Optional;



import com.kosta.catdog.entity.DesFileVo;
import com.kosta.catdog.entity.ShopFileVO;
import com.kosta.catdog.repository.DesFileVORepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;

import com.kosta.catdog.entity.DesFileVo;
import com.kosta.catdog.entity.DesGallery;
import com.kosta.catdog.entity.Designer;

import com.kosta.catdog.entity.PetFileVO;
import com.kosta.catdog.entity.Review;
import com.kosta.catdog.repository.DesFileVORepository;

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
	
	@Value("${des.upload.dir}")
	private String uploadDir; //파일이 업로드 되는dir

	@Override
	public void addDesignerInfo(Designer designer) throws Exception {
		designerRepository.save(designer);
	}
		
	@Override
	public Double avgStarCountByDesigner(Integer num) throws Exception {
		return userDslRepository.findAvgStarCountByDesigner(num);
	}

	@Override
	public void fileView(Integer num, OutputStream out) throws Exception {
		try {
		Integer fileNum = Integer.parseInt(designerRepository.findById(num).get().getProfImg() );
		Optional<DesFileVo> fileVoOptional  = desFileVORepository.findById(fileNum);
		DesFileVo fileVo = fileVoOptional.get();
//		FileCopyUtils.copy(fileVo.getData(), out); //데이타 뿌려주기
		FileInputStream fis = new FileInputStream(fileVo.getDir()+fileNum);//폴더에서 가져오기 
		FileCopyUtils.copy(fis, out);
		out.flush();
	} catch (Exception e) {
		e.printStackTrace();
	}
	}

	public Designer desreg(Designer des, List<MultipartFile> files) throws Exception {
		if(files!=null && files.size() !=0 ) {
			String fileNums = "";
			for (MultipartFile file : files) {

				Date today = Date.valueOf(LocalDate.now());


				DesFileVo fileVO = new DesFileVo();
				fileVO.setDir(uploadDir);
				fileVO.setName(file.getOriginalFilename());
				fileVO.setSize(file.getSize());
				fileVO.setType(file.getContentType());
				fileVO.setDate(today);
				//fileVO.setData(file.getBytes());
				desFileVORepository.save(fileVO);

				File uploadFile = new File(uploadDir + fileVO.getNum());
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
