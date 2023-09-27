package com.hotel.controller;

import java.util.List;

import com.hotel.config.RateLimit;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.hotel.model.Hotel;
import com.hotel.service.HotelService;

@RestController
@RequestMapping("/hotel")
public class HotelController {
	
	@Autowired
	HotelService hotelService;
	
	@GetMapping("/home")
	public String home() {
		return "Hotel";
	}
	
	
	@GetMapping("/all")
	public List<Hotel> all(){
		return hotelService.allhotel();
	}

	@PostMapping("/add")
	public Hotel add(@RequestBody Hotel hotel) {
		return hotelService.save(hotel);
	}
	@GetMapping("/{hotelId}")
	@RateLimit
	public ResponseEntity<Hotel> createHotel(@PathVariable("hotelId") int hotelId) {
		return ResponseEntity.status(HttpStatus.OK).body(hotelService.get(hotelId));
	}


}
