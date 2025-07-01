package com.example.project.remodelingList.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class CreateRemodelingListDto {
	@JsonProperty("no")
	private int rNo;
	
	@JsonProperty("uId")
	private String uId;
}