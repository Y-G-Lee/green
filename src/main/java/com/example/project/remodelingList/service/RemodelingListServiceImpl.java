package com.example.project.remodelingList.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.project.remodelingList.dto.CreateRemodelingListDto;
import com.example.project.remodelingList.dto.RemodelingListDto;
import com.example.project.remodelingList.repository.RemodelingListMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RemodelingListServiceImpl implements RemodelingListService{
	private final RemodelingListMapper remodelingListMapper;

	@Override
	public List<RemodelingListDto> getAllRemodeling() {
		return remodelingListMapper.findAllRemodeling();
	}

	@Override
	public RemodelingListDto getRemodeling(int no) {
		return remodelingListMapper.findRemodlingNo(no);
	}
	
	@Override
	public void createAcception(CreateRemodelingListDto createRemodelingListDto) {
		RemodelingListDto remodeling = this.getRemodeling(createRemodelingListDto.getRNo());
		if(remodeling != null) {
			remodelingListMapper.insertRemodelingList(createRemodelingListDto);
		}
	}


}
