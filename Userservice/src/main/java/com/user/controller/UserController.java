package com.user.controller;

import com.user.model.User;
import com.user.service.UserService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private RestTemplate restTemplate;
	
	@GetMapping("/home")
	public String Home() {
		return "Hello world";
	}
	
		@PostMapping("/add")
	public ResponseEntity<User> add(@RequestBody User user) {
		User user1 = userService.save(user);
		return  ResponseEntity.status(HttpStatus.OK).body(user);
	}
	
	@GetMapping("/all")
	public List<User> gelall() {
		return  userService.getAlluser();
	}
	
	
	@GetMapping("/{id}")
	@CircuitBreaker(name = "ratingHotelBreaker",fallbackMethod = "ratingHotelFallback")
//	@Retry(name = "ratingHotelService", fallbackMethod = "ratingHotelFallback")
	public ResponseEntity<User> getByID(@PathVariable int id) {
		return ResponseEntity.status(HttpStatus.CREATED).body(userService.getbyId(id));

	}
	
	@DeleteMapping("/deletebyid/{id}")
	public Object deletebyid(@PathVariable int id) {
		return userService.deletebyId(id);
	}


	public ResponseEntity<User> ratingHotelFallback( int id,Exception  ex) {
		System.out.println("fallback is executed because service in down....."+ex.getMessage());

		User dummy = User.builder().name("dummy")
				.id(1).build();
		return ResponseEntity.status(HttpStatus.OK).body(dummy );

	}

}
