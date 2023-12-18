package com.kosta.catdog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kosta.catdog.entity.UserGalleryLike;

public interface UserGalleryLikeRepository extends JpaRepository<UserGalleryLike, Integer> {
	
}
