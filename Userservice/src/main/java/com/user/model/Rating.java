package com.user.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Rating {


	private int ratingid;
	private int userid;
	private int hotelid;
	private double rating;
	private  String feedback;

	private  Hotel hotel;



}
