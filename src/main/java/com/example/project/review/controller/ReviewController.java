package com.example.project.review.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.example.project.review.dto.CreateReviewDto;
import com.example.project.review.dto.ReviewImgDto;
import com.example.project.review.dto.ReviewsDto;
import com.example.project.review.service.ReviewService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ReviewController {
	private final ReviewService service;

	@PostMapping("/review")
	public ResponseEntity<Integer> createReview(@RequestBody CreateReviewDto dto) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String userId = ((UserDetails) authentication.getPrincipal()).getUsername();
		dto.setUId(userId);
		service.createReview(dto);
		return ResponseEntity.ok(dto.getNo());
	}

	@PostMapping("/uploadRImage")
	public ResponseEntity<?> uploadImages(@RequestParam("reNo") int reNo,
			@RequestParam("files") List<MultipartFile> files) {
		String uploadDir = "C:/녹색제품 이미지/api/";
		List<ReviewImgDto> imageDtos = new ArrayList<>();


		try {
			int index = 1;
			for (MultipartFile file : files) {
				String ext = Optional.ofNullable(file.getOriginalFilename()).filter(f -> f.contains("."))
						.map(f -> f.substring(f.lastIndexOf("."))).orElse(".jpg");
				String filename = reNo + "_" + index++ + ext;

				File saveFile = new File(uploadDir + filename);
				file.transferTo(saveFile);  

				ReviewImgDto dto = new ReviewImgDto();
				dto.setReNo(reNo);
				dto.setRImageName(filename);
				dto.setRImage(filename);

				imageDtos.add(dto);
			}

			service.saveRImages(imageDtos);
			return ResponseEntity.ok("이미지 저장 완료");
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("이미지 저장 실패");
		}
	}

	@GetMapping("/reviews")
	public ResponseEntity<List<ReviewsDto>> getReviews(@RequestParam("pId") String pId) {
		List<ReviewsDto> reviews = service.findByPid(pId);
		return ResponseEntity.ok(reviews);
	}

	@GetMapping("/allreview")
	public ResponseEntity<List<ReviewsDto>> getAllReview(ReviewsDto Dto) {
		List<ReviewsDto> reviews = service.findReviews(Dto);
		return ResponseEntity.ok(reviews);
	}

	@PostMapping("/deleteReview")
	public ResponseEntity<Void> deleteReview(@RequestBody ReviewsDto Dto) {
		int no = Dto.getNo();
		service.deleteReview(no);
		return ResponseEntity.ok().build();
	}
	
	@PostMapping("/deleteReviewImg")
	public ResponseEntity<Void> deleteReviewImg(@RequestBody ReviewsDto dto) {
		int no = dto.getNo();  
		service.deleteReviewImg(no); 
		return ResponseEntity.ok().build();
	}
}