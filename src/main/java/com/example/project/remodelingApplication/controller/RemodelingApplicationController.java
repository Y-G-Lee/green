package com.example.project.remodelingApplication.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.project.remodelingApplication.dto.RemodelingApplicationDto;
import com.example.project.remodelingApplication.service.RemodelingApplicationService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class RemodelingApplicationController {
	private final RemodelingApplicationService remodelingApplicationService;
	
	@GetMapping("/business/{uId}")
	public ResponseEntity<List<RemodelingApplicationDto>> getAllRemodeling(@PathVariable("uId") String uId) {
		return ResponseEntity.ok(remodelingApplicationService.getAllList(uId));
	}
	
	@GetMapping("/user/{uId}")
	public ResponseEntity<List<RemodelingApplicationDto>> getUser(@PathVariable("uId") String uId) {
		return ResponseEntity.ok(remodelingApplicationService.getRemodeling(uId));
	}
}
