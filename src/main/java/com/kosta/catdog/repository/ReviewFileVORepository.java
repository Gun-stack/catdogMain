package com.kosta.catdog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kosta.catdog.entity.PetFileVO;
import com.kosta.catdog.entity.ReviewFileVO;

public interface ReviewFileVORepository extends JpaRepository<ReviewFileVO, Integer> {

	
}
