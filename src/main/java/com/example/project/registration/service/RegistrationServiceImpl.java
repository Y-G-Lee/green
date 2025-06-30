package com.example.project.registration.service;

import org.springframework.stereotype.Service;

import com.example.project.registration.dto.RegistrationDto;
import com.example.project.registration.repository.RegistrationMapper;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class RegistrationServiceImpl implements RegistrationService{
	private final RegistrationMapper mapper;
	
	@Override
	public void createRegistration(RegistrationDto registrationDto) {
		mapper.createRegistration(registrationDto);
		
	}

	@Override
	public void deleteRegistration(String uId) {
		mapper.deleteRegistration(uId);
		
	}

}
