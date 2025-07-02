package com.example.project.review.service;

import java.util.List;

import com.example.project.review.dto.CreateReviewDto;
import com.example.project.review.dto.ReviewImgDto;
import com.example.project.review.dto.ReviewsDto;

public interface ReviewService {
	void createReview(CreateReviewDto createReviewDto);
	void saveRImages(List<ReviewImgDto> img);
	List<ReviewsDto> findByPid(String pId);
	List<ReviewsDto> findReviews(ReviewsDto Dto);
	void deleteReview(int no);
	void deleteReviewImg(int reNo);
}
