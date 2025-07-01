package com.example.project.orderList.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class OrderListDto {
	
	@JsonProperty("oNo")
	private int oNo;
	
	@JsonProperty("uId")
	private String uId;
	private String images;
	private String name;
	private int prices;
	private int mileage;
	private String product_id;
	
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDateTime orderTime;

}