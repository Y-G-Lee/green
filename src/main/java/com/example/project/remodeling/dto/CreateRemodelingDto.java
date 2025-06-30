package com.example.project.remodeling.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class CreateRemodelingDto {
	@JsonProperty("uId")
	private String uId;
	private int roomSize;
	private int room;
	private int bathroom;
	private String address;
	private int ho;
	private int dong;
	private int windows;
	private int floor;
	
	@JsonProperty("break")
	private int brea;
	
	private int totalSum;
}
