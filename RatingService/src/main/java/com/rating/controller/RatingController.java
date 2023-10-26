package com.rating.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.rating.model.Rating;
import com.rating.service.RatingService;

@RestController
@RequestMapping("/rating")
public class RatingController {

    @Autowired
    private RatingService ratingService;

    @GetMapping("/home")
    public String home() {
        return "hello world";
    }

    @PostMapping("/addrating")
    public Rating save(@RequestBody Rating rating) {
        return ratingService.save(rating);
    }

    @GetMapping("/user/{id}")
    public List<Rating> ratingByuserId(@PathVariable int id) {
        return ratingService.byUserId(id);
    }

    @GetMapping("/allrating")
    public List<Rating> allRating(Rating rating) {
        return ratingService.allRating(rating);
    }

}
