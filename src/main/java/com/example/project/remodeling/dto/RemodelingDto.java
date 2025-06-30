package com.example.project.remodeling.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class RemodelingDto {
	@JsonProperty("uId")
	private String uId;
	private int roomSize;
	private int room;
	private int bathroom;
	private int windows;
	private int light;
	private int floor;
	
	@JsonProperty("break")
	private int brea;
	
	private String address;
	private int totalsum;
	private int dong;
	private int ho;
}
