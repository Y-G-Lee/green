package com.example.project.orderList.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class OrderListDto {
	@JsonProperty("uId")
	private String uId;
	private String images;
	private String name;
	private int prices;
	private int mileage;
	private String productId;

}
