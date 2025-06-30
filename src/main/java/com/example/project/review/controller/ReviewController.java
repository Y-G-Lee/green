package com.example.project.review.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import com.example.project.review.dto.CreateReviewDto;
import com.example.project.review.service.ReviewService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/api")
public class ReviewController {
    private final ReviewService service;

    @PostMapping("/review")
    public void createReview(@RequestBody CreateReviewDto dto) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userId = ((UserDetails) authentication.getPrincipal()).getUsername();
        dto.setUId(userId); 
        service.createReview(dto);
    }
}
