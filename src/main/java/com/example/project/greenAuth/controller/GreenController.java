package com.example.project.greenAuth.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.example.project.greenAuth.dto.GreenAuthDto;
import com.example.project.greenAuth.service.GreenAuthService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/api")
public class GreenController {
	private final GreenAuthService service;

	@GetMapping("/green-check")
	public ResponseEntity<?> getGreenAuth(@RequestParam("authNum") String authNum) {
		GreenAuthDto result = service.getNum(authNum);

		if (result == null) {
			return ResponseEntity.status(404).body("해당 인증번호에 대한 정보를 찾을 수 없습니다.");
		}

		return ResponseEntity.ok(result);
	}
}
