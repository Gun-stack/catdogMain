package com.kosta.catdog.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.kosta.catdog.entity.Pet;

public interface PetService {

	Pet petReg(Pet pet ,List<MultipartFile> files) throws Exception;
}
