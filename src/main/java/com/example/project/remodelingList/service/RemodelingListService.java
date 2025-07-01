package com.example.project.remodelingList.service;

import java.util.List;

import com.example.project.remodelingList.dto.CreateRemodelingListDto;
import com.example.project.remodelingList.dto.RemodelingListDto;

public interface RemodelingListService {
	List<RemodelingListDto> getAllRemodeling();
	void createAcception(CreateRemodelingListDto createRemodelingListDto);
	RemodelingListDto getRemodeling(int no);
}
