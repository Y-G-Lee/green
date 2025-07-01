package com.example.project.remodelingList.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.project.remodelingList.dto.CreateRemodelingListDto;
import com.example.project.remodelingList.dto.RemodelingListDto;

@Mapper
public interface RemodelingListMapper {
	List<RemodelingListDto> findAllRemodeling();
	RemodelingListDto findRemodlingNo(int no);
	void insertRemodelingList(CreateRemodelingListDto createRemodelingListDto);
}
