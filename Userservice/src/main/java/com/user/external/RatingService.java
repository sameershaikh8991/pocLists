package com.user.external;

import com.user.model.Rating;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "RATING-SERVICE")
public interface RatingService {

    @GetMapping("/rating/user/{userid}")
    public Rating getRating(@PathVariable("userid") int userid);

}
