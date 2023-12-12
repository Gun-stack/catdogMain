package com.kosta.catdog.service;

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

	// 미용사 등록
	Designer desreg(Designer des, List<MultipartFile> files) throws Exception;
}