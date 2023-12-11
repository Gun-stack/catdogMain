package com.kosta.catdog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kosta.catdog.entity.DesGallery;
import com.kosta.catdog.entity.Designer;
import com.kosta.catdog.entity.Review;
import com.kosta.catdog.repository.DesignerRepository;
import com.kosta.catdog.repository.UserDslRepository;

@Service
public class DesignerServiceImpl implements DesignerService {
	
	@Autowired
	private UserDslRepository userDslRepository;
	@Autowired
	private DesignerRepository designerRepository;

	@Override
	public void addDesignerInfo(Designer designer) throws Exception {
		designerRepository.save(designer);
	}
	
	@Override
	public DesGallery desGalleryByDesigner(Integer num) throws Exception {
		return userDslRepository.findDesGalleryByDesigner(num);
	}

	@Override
	public List<DesGallery> desGalleryListByDesigner(String desId) throws Exception {
		return userDslRepository.findDesGalleryListByDesigner(desId);
	}

	@Override
	public Double avgStarCountByDesigner(Integer num) throws Exception {
		return userDslRepository.findAvgStarCountByDesigner(num);
	}
	
}
