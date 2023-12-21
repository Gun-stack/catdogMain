package com.kosta.catdog.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;

import com.kosta.catdog.entity.UserGallery;

public interface UserGalleryRepository extends JpaRepository<UserGallery, Integer> {
	Slice<UserGallery> findByTagContainingIgnoreCase(String tag, Pageable pageable);
	
}
