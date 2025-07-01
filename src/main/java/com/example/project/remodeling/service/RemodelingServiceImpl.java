package com.example.project.remodeling.service;

import java.util.List;

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
		if(room == null) {
			 throw new NoDataFoundException("해당 조건에 맞는 리모델링 데이터가 없습니다.");
		}
		remodelingMapper.saveRemodelingResult(createRemodelingDto);
	}
	
	public class NoDataFoundException extends RuntimeException {
	    public NoDataFoundException(String message) {
	        super(message);
	    }
	}

	@Override
	public RemodelingDto getUid(String uId) {
		return remodelingMapper.findByUid(uId);
	}

}
