package com.kosta.catdog.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import com.kosta.catdog.entity.Pet;
import com.kosta.catdog.entity.PetFileVO;
import com.kosta.catdog.repository.PetFileVORepository;
import com.kosta.catdog.repository.PetRepository;

@Service
public class PetServiceImpl implements PetService {
	
	@Autowired 
	private PetRepository petRepository;
	
	@Autowired 
	private PetFileVORepository petFileVORepository;
	
	@Override
	public Pet petReg(Pet pet, List<MultipartFile> files) throws Exception {
		String dir = "c:/kkw/upload/pet";
		if(files!=null && files.size() !=0 ) {
			String fileNums="";
			for(MultipartFile file : files) {
				
			Date today = Date.valueOf(LocalDate.now());

				
			PetFileVO fileVO = new PetFileVO();
			fileVO.setDir(dir);
			fileVO.setName(file.getOriginalFilename());
			fileVO.setSize(file.getSize());
			fileVO.setType(file.getContentType());
			fileVO.setData(file.getBytes());
			fileVO.setDate(today);
			petFileVORepository.save(fileVO);
			
			File uploadFile= new File(dir+fileVO.getNum());
			file.transferTo(uploadFile);
			if(!fileNums.equals(""))
				fileNums += ",";
			fileNums += fileVO.getNum();
			}
			pet.setImg(fileNums);
		}
		petRepository.save(pet);
		return pet;
	}
	@Override
	public void fileView(Integer num, OutputStream out) throws Exception {
		try {
			Optional<PetFileVO> fileVoOptional  = petFileVORepository.findById(num);
			PetFileVO fileVo = fileVoOptional.get();
			
			FileCopyUtils.copy(fileVo.getData(), out); //데이타 뿌려주기
			FileInputStream fis = new FileInputStream(fileVo.getDir()+num);//폴더에서 가져오기 
//			String dir= "c:kkw/upload/";
//			FileInputStream fis = new FileInputStream(dir+num);
			FileCopyUtils.copy(fis, out);
			out.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
}
