package com.example.project.registration.repository;

import org.apache.ibatis.annotations.Mapper;

import com.example.project.registration.dto.RegistrationDto;

@Mapper
public interface RegistrationMapper {
	void createRegistration (RegistrationDto registrationDto);
	
}
