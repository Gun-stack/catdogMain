package com.kosta.catdog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kosta.catdog.entity.Pet;

public interface PetRepository extends JpaRepository<Pet, Integer> {

	
}
