package com.kosta.catdog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kosta.catdog.entity.UserGallery;

public interface UserGalleryRepository extends JpaRepository<UserGallery, Integer> {
	
}
