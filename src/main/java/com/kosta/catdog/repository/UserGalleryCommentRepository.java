package com.kosta.catdog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kosta.catdog.entity.UserGalleryComment;

public interface UserGalleryCommentRepository extends JpaRepository<UserGalleryComment, Integer> {

}
