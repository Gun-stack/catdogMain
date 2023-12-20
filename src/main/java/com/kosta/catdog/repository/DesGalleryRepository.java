package com.kosta.catdog.repository;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;

import com.kosta.catdog.entity.DesGallery;

public interface DesGalleryRepository extends JpaRepository<DesGallery, Integer> {
}
