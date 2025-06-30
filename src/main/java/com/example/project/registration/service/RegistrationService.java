package com.example.project.registration.service;

import com.example.project.registration.dto.RegistrationDto;

public interface RegistrationService {
	void createRegistration(RegistrationDto registrationDto);
	void deleteRegistration(String uId);
}
