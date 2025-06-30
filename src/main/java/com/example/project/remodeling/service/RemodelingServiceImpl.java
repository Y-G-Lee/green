package com.example.project.remodeling.service;

import org.springframework.stereotype.Service;

import com.example.project.remodeling.dto.CreateRemodelingDto;
import com.example.project.remodeling.dto.RemodelingDto;
import com.example.project.remodeling.repository.RemodelingMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RemodelingServiceImpl implements RemodelingService{
	private final RemodelingMapper remodelingMapper;
	@Override
	public RemodelingDto getRoom(int roomSize, int room, int bathrom) {
		return remodelingMapper.findRoomSize(roomSize, room, bathrom);
	}
	
	@Override
	public void createRemodeling(CreateRemodelingDto createRemodelingDto) {
		RemodelingDto room = this.getRoom(createRemodelingDto.getRoomSize(), createRemodelingDto.getRoom(),createRemodelingDto.getBathroom());
		System.out.println("uId: " + createRemodelingDto.getUId());
		if(room != null) {
			remodelingMapper.saveRemodelingResult(createRemodelingDto);
		}
	}

}
