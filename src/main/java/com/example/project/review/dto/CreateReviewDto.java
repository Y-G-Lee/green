package com.example.project.review.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class CreateReviewDto {
	@JsonProperty("uId")
	private String uId;
	@JsonProperty("pId")
	private String pId;
	@JsonProperty("rReview")
	private String rReview;
	private int no;
	private double rating;
}