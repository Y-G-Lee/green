package com.example.project.remodelingApplication.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.project.remodelingApplication.dto.RemodelingApplicationDto;

@Mapper
public interface RemodelingApplicationMapper {
	List<RemodelingApplicationDto> findRemodeling(String uId);
	List<RemodelingApplicationDto> findAllList();
}
