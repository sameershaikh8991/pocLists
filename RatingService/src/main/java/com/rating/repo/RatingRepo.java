package com.rating.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rating.model.Rating;

import java.util.List;
import java.util.Optional;


@Repository
public interface RatingRepo  extends JpaRepository<Rating, Integer>{

    List<Rating> findByUserid(int id);
}
