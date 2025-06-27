package com.example.project.user.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class UserRoleDto {
	private String role;
	@JsonProperty("uId")
	private String uId;
}
