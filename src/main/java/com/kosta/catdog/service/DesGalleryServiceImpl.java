package com.kosta.catdog.service;

import java.io.OutputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import com.kosta.catdog.entity.DesGallery;
import com.kosta.catdog.repository.DesGalleryRepository;
import com.kosta.catdog.repository.UserDslRepository;

public class DesGalleryServiceImpl implements DesGalleryService {

	@Autowired
	private UserDslRepository userDslRepository;
	@Autowired
	private DesGalleryRepository desGalleryRepository;
	
	@Override
	public DesGallery registerDesGallery(DesGallery desGallery, List<MultipartFile> files) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void modifyDesGallery(Integer num) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteDesGallery(Integer num) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public DesGallery findDesGallery(Integer num) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<DesGallery> desGalleryListMainPage(Integer num, int offset, int limit) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<DesGallery> desGalleryListShopPage(Integer num, int offset, int limit) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<DesGallery> desGalleryListDesignerPage(Integer num, int offset, int limit) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void fileView(Integer num, OutputStream out) throws Exception {
		// TODO Auto-generated method stub
		
	}

}
