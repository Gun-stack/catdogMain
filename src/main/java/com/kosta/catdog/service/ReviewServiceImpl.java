package com.kosta.catdog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kosta.catdog.entity.Review;
import com.kosta.catdog.repository.ReviewRepository;
import com.kosta.catdog.repository.UserDslRepository;

@Service
public class ReviewServiceImpl implements ReviewService {

	@Autowired
	private UserDslRepository userDslRepository;
	@Autowired
	private ReviewRepository reviewRepository;
	
	@Override
	public void postReview(Review review) throws Exception {
		reviewRepository.save(review);
	}

	@Override
	public Review findReview(Integer num) throws Exception {
		return userDslRepository.findReview(num);
	}

	@Override
	public void modifyReveiw(Review review) throws Exception {
		reviewRepository.save(review);
	}

	@Override
	public void deleteReview(Integer num) throws Exception {
		reviewRepository.deleteById(num);
	}

}