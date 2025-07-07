package com.example.project.cart.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class UpdateOrderDto {
	@JsonProperty("uId")
	private String uId;
	private int mileage;
	private int usedPoint;
}