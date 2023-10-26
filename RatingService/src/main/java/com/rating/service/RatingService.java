package com.rating.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.rating.model.Rating;
import com.rating.repo.RatingRepo;

@Service
public class RatingService {
	
	@Autowired
	RatingRepo ratingRepo;
	
	public Rating  save(Rating rating) {
		return ratingRepo.save(rating);
	}
	
	
	public List<Rating> allRating(Rating rating){
		return ratingRepo.findAll();
	}
	
	public List<Rating> byUserId(int id) {
		return  ratingRepo.findByUserid(id);
	}
}
