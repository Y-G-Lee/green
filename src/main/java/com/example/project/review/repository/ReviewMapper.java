package com.example.project.review.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.project.review.dto.CreateReviewDto;
import com.example.project.review.dto.ReviewImgDto;
import com.example.project.review.dto.ReviewsDto;

@Mapper
public interface ReviewMapper {
	void createReview(CreateReviewDto CreateReviewDto);
	void insertRImage(ReviewImgDto image);
	List<ReviewsDto> findByPid(String pId);
	List<ReviewsDto> findReview(ReviewsDto Dto);
	void deleteReview(@Param("no") int no);
	void deleteReviewImg(@Param("reNo") int reNo);
}
