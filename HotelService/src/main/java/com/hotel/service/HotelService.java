package com.hotel.service;

import java.util.List;
import java.util.Optional;

import com.hotel.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hotel.model.Hotel;
import com.hotel.repo.HotelRepo;

@Service
public class HotelService {
	
	
	@Autowired
	HotelRepo hotelRepo;
	
	public Hotel save(Hotel hotel) {
		return hotelRepo.save(hotel);
	}
	
	public List<Hotel> allhotel() {
		return hotelRepo.findAll();
	}

	public Hotel get(int id) {
		return hotelRepo.findById(id).orElseThrow( () ->  new ResourceNotFoundException());
	}
}
