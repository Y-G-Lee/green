package com.example.project.remodelingList.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class RemodelingListDto {
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
	private LocalDateTime applicationDate;
	
	@JsonProperty("no")
	private int no;
}
