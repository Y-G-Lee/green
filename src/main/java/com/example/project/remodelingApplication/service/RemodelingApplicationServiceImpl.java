package com.example.project.remodelingApplication.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.project.remodelingApplication.dto.RemodelingApplicationDto;
import com.example.project.remodelingApplication.repository.RemodelingApplicationMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RemodelingApplicationServiceImpl implements RemodelingApplicationService{
	private final RemodelingApplicationMapper remodelingApplicationMapper;

	@Override
	public List<RemodelingApplicationDto> getAllList(String uId) {
		return remodelingApplicationMapper.findAllList(uId);
	}

	@Override
	public List<RemodelingApplicationDto> getRemodeling(String uId) {
		return remodelingApplicationMapper.findRemodeling(uId);
	}
}
