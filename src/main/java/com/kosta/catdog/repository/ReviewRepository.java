package com.kosta.catdog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kosta.catdog.entity.Review;

public interface ReviewRepository extends JpaRepository<Review, Integer> {

}
