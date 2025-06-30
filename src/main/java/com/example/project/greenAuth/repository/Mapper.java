package com.example.project.greenAuth.repository;

import org.apache.ibatis.annotations.Param;

import com.example.project.greenAuth.dto.GreenAuthDto;

@org.apache.ibatis.annotations.Mapper
public interface Mapper {
	GreenAuthDto findNum(@Param("authNum") String authNum);
}
