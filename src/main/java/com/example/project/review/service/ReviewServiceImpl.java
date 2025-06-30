package com.example.project.review.service;

import org.springframework.stereotype.Service;

import com.example.project.review.dto.CreateReviewDto;
import com.example.project.review.repository.ReviewMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService{
	private final ReviewMapper mapper;
	@Override
	public void createReview(CreateReviewDto createReviewDto) {
		mapper.createReview(createReviewDto);
	}

}
