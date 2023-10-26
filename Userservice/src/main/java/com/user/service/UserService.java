package com.user.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.user.external.RatingService;
import com.user.model.Hotel;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.*;
//import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
//import org.springframework.web.client.RestTemplate;
import org.springframework.web.client.RestTemplate;

import com.user.exception.CustomeException;
import com.user.model.Rating;
import com.user.model.User;
import com.user.repo.UserRepo;






@Service
@Slf4j
public class UserService {

	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private RestTemplate restTemplate;

//	@Autowired
//	private RatingService ratingService;

	public User save(User user) {
		return userRepo.save(user);
	}

	public List<User> getAlluser(){
		return userRepo.findAll();
	}
	public User getbyId( int id) {

		String url = "http://localhost:9092/rating/user/"+id;
//
		User user = userRepo.findById(id).orElseThrow( () -> new CustomeException("not found"));

//
		Rating[] ratingsOfUser = restTemplate.getForObject(url , Rating[].class);
		log.info("{} ", ratingsOfUser);
		List<Rating> ratings = Arrays.stream(ratingsOfUser).collect(Collectors.toList());
		log.info("{}",ratings);


		List<Rating> ratingList = ratings.stream().map(rating -> {
			log.info("{}",rating.getHotelid());
			ResponseEntity<Hotel> forEntity=  restTemplate.getForEntity("http://localhost:9093/hotel/"+rating.getHotelid(),Hotel.class);
			Hotel hotel = forEntity.getBody();
			rating.setHotel(hotel);
			return rating;

		}).collect(Collectors.toList());

		user.setRatings(ratingList);

		return user;
	}
	
	public Object deletebyId(int id) {
		userRepo.deleteById(id);
		return id;
	}
	
}
