package com.example.project.remodelingList.controller;

import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.project.remodelingList.dto.CreateRemodelingListDto;
import com.example.project.remodelingList.dto.RemodelingListDto;
import com.example.project.remodelingList.service.RemodelingListService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class RemodelingListController {
	private final RemodelingListService remodelingListService;
	
	@GetMapping("/findAllRemodeling")
	public ResponseEntity<List<RemodelingListDto>> getAllRemodeling() {
		return ResponseEntity.ok(remodelingListService.getAllRemodeling());
	}
	
	@PostMapping("/remodelingList")
	public ResponseEntity<Map<String, Object>> createRemodeling(@RequestBody CreateRemodelingListDto createRemodelingListDto) {
//		remodelingListService.createAcception(createRemodelingListDto);
//		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("{no}").buildAndExpand(createRemodelingListDto.getRNo()).toUri();
//		return ResponseEntity.created(uri).build();
		 try {
		        remodelingListService.createAcception(createRemodelingListDto);
		        Map<String, Object> result = new HashMap<>();
		        result.put("success", true);
		        return ResponseEntity.ok(result);
		    } catch (Exception e) {
		        Map<String, Object> result = new HashMap<>();
		        result.put("success", false);
		        result.put("message", e.getMessage());
		        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
		    }
	}
}
