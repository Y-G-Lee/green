package com.example.project.remodeling.controller;

import java.net.URI;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.project.remodeling.dto.CreateRemodelingDto;
import com.example.project.remodeling.dto.RemodelingDto;
import com.example.project.remodeling.service.RemodelingService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class RemodelingController {
	private final RemodelingService remodelingService;
	
	@PostMapping("/remodeling")
	public ResponseEntity<RemodelingDto> getRoom(@PathVariable("roomSize") int roomSize, @PathVariable("room") int room, @PathVariable("bathroom") int bathroom) {
		return ResponseEntity.ok(remodelingService.getRoom(roomSize, room, bathroom));
	}
	
	@PostMapping("/save-remodeling")
	public ResponseEntity<Void> createRemodeling(@RequestBody CreateRemodelingDto createRemodelingDto) {
		remodelingService.createRemodeling(createRemodelingDto);
		URI uri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/{roomSize}&{room}&{bathroom}").buildAndExpand(createRemodelingDto.getRoomSize(),createRemodelingDto.getRoom(),createRemodelingDto.getBathroom()).toUri();
		return ResponseEntity.created(uri).build();
	}
}
