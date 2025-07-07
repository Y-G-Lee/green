package com.example.project.review.dto;

import java.util.Date;
import java.util.List;

import lombok.Data;

@Data
public class ReviewsDto {
	private int no;
	private String uId;
	private String pId;
	private String name;
	private String rReview;
	private Date rDate; 
	private double rating;
	List<ReviewImgDto> rImages;
}
