package com.kosta.catdog.service;


import java.io.OutputStream;
import java.util.List;
import com.kosta.catdog.entity.DesGallery;

import com.kosta.catdog.entity.Designer;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface DesignerService {
	// 프로필 정보 등록
	void addDesignerInfo(Designer designer) throws Exception;
	// 프로필 정보 수정 - 화면 정해지면 작성할 것
	// void modifyDesignerInfo(Designer designer) throws Exception;
	// 별점 산출(디자이너별 등록된 모든 리뷰를 대상으로 별점 평균 산출해서 보여주기)
	Double avgStarCountByDesigner(Integer num) throws Exception;

	
	
	//디자이너 프로필사진조회
	void fileView(Integer num, OutputStream out) throws Exception ;


	// 미용사 등록
	Designer desreg(Designer des, List<MultipartFile> files) throws Exception;

	// 미용사 조회
	Designer selectDes(Integer num) throws Exception;

	//미용사 직팩 변경
	void modipostition(Designer des) throws Exception;

}