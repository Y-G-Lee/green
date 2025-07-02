package com.example.project.review.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.project.review.dto.CreateReviewDto;
import com.example.project.review.dto.ReviewImgDto;
import com.example.project.review.dto.ReviewsDto;
import com.example.project.review.repository.ReviewMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {
	private final ReviewMapper mapper;

	@Override
	public void createReview(CreateReviewDto createReviewDto) {
		mapper.createReview(createReviewDto);
	}

	@Transactional
	@Override
	public void saveRImages(List<ReviewImgDto> img) {
		for (ReviewImgDto image : img) {
			mapper.insertRImage(image);
		}
	}

	@Override
	public List<ReviewsDto> findByPid(String pId) {
		return mapper.findByPid(pId);
	}

	@Override
	public List<ReviewsDto> findReviews(ReviewsDto Dto) {
		return mapper.findReview(Dto);
	}

	@Override
	public void deleteReview(int no) {
		mapper.deleteReview(no);
	}

	@Override
	public void deleteReviewImg(int reNo) {
		mapper.deleteReviewImg(reNo);
	}
	
	
}