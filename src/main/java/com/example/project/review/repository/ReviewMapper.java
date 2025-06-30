package com.example.project.review.repository;

import org.apache.ibatis.annotations.Mapper;

import com.example.project.review.dto.CreateReviewDto;

@Mapper
public interface ReviewMapper {
	void createReview(CreateReviewDto CreateReviewDto);
}
