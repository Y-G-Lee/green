package com.example.project.product.dto;

import lombok.Data;

@Data
public class CreateProductDto {
	private String productId;
	private String name;
	private String makeDate;
	private String classification;
	private String registrationNum;
	private String company;
	private int prices;
	private int mileage;
	
}
