package com.example.project.greenAuth.service;

import org.springframework.stereotype.Service;
import com.example.project.greenAuth.dto.GreenAuthDto;
import com.example.project.greenAuth.repository.Mapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class GreenAuthServiceImpl implements GreenAuthService{
	private final Mapper mapper;
	@Override
	public GreenAuthDto getNum(String authNum) {
		return mapper.findNum(authNum);
	}

}
