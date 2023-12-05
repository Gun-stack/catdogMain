package com.kosta.catdog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kosta.catdog.entity.PetFileVO;

public interface PetFileVORepository extends JpaRepository<PetFileVO, Integer> {

	
}
