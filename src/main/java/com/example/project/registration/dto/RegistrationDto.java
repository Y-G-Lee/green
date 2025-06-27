package com.example.project.registration.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class RegistrationDto {
	private String registrationNum;
	@JsonProperty("uId")
	private String uId;
	private String bImage;
}
