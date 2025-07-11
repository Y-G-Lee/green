package com.example.project.cart.dto;

import java.time.LocalDateTime;
import java.util.List;

import lombok.Data;

@Data
public class OrderDto {
	private int no;
	private String uId;
	private String pId;
	private LocalDateTime orderTime;
	private int quantity;
	private String memo;
	private String address;
	private String detailAddress;
	private int mileage;
	private int totalMileage;
	private int usedPoint;
}
