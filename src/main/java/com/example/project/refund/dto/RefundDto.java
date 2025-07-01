package com.example.project.refund.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class RefundDto {
	
	@JsonProperty("orderNo")
	private int oNo;
	
    private String why;
    private String address;
    private String detailAddress;
}
