package com.example.project.remodeling.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.project.remodeling.dto.CreateRemodelingDto;
import com.example.project.remodeling.dto.RemodelingDto;

@Mapper
public interface RemodelingMapper {
	RemodelingDto findRoomSize(@Param("roomSize") int roomSize,@Param("room") int room,@Param("bathroom") int bathroom);
	void saveRemodelingResult(CreateRemodelingDto createRemodelingDto);
	RemodelingDto findByUid(@Param("uId") String uId);
}
