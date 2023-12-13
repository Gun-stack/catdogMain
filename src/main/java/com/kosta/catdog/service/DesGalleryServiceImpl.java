package com.kosta.catdog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

import com.kosta.catdog.entity.DesGallery;
import com.kosta.catdog.repository.DesGalleryRepository;
import com.kosta.catdog.repository.UserDslRepository;
@Service
public class DesGalleryServiceImpl implements DesGalleryService {

	@Autowired
	private UserDslRepository userDslRepository;
	@Autowired
	private DesGalleryRepository desGalleryRepository;
	
	@Override
	public void registerDesGallery(DesGallery desGallery) throws Exception {
		desGalleryRepository.save(desGallery);
	}

	@Override
	public void modifyDesGallery(DesGallery desGallery) throws Exception {
		desGalleryRepository.save(desGallery);
	}

	@Override
	public void deleteDesGallery(Integer num) throws Exception {
		desGalleryRepository.deleteById(num);
	}

	@Override
	public DesGallery findDesGallery(Integer num) throws Exception {
		return userDslRepository.findDesGallery(num);
	}

	@Override
	public Slice<DesGallery> desGalleryListMainPage(Pageable pageable) throws Exception {
		return desGalleryRepository.findAll(pageable);
	}

	@Override
	public List<DesGallery> desGalleryListShopPage(Integer num, int offset, int limit) throws Exception {
		return userDslRepository.findDesGalleryListShopPage(num, offset, limit);
	}

	@Override
	public List<DesGallery> desGalleryListDesignerPage(Integer num, int offset, int limit) throws Exception {
		return userDslRepository.findDesGalleryListDesignerPage(num, offset, limit);
	}
}
