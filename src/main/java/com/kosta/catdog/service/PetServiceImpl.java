package com.kosta.catdog.service;

import java.io.File;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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
	
	
	
}
