package com.kosta.catdog.service;

import java.io.OutputStream;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.kosta.catdog.entity.Pet;

public interface PetService {

	Pet petReg(Pet pet ,List<MultipartFile> files) throws Exception;
	void fileView(Integer num, OutputStream out) throws Exception ;
}
