package com.kosta.catdog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kosta.catdog.entity.Designer;
import com.kosta.catdog.entity.Reservation;

public interface DesignerRepository extends JpaRepository<Designer, Integer> {
	
}
