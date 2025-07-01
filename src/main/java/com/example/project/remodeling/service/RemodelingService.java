package com.example.project.remodeling.service;

import java.util.List;

import com.example.project.remodeling.dto.CreateRemodelingDto;
import com.example.project.remodeling.dto.RemodelingDto;

public interface RemodelingService {
	RemodelingDto getRoom(int roomSize, int room, int bathrom);
	void createRemodeling(CreateRemodelingDto createRemodelingDto);
	RemodelingDto getUid(String uId);
}
