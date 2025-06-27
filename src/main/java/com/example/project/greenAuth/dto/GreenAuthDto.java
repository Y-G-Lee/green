package com.example.project.greenAuth.dto;

import java.time.LocalDate;

import lombok.Data;

@Data
public class GreenAuthDto {
	private int no;
	private String authNum;
	private String name;
	private String company;
	private String part;
	private LocalDate day;
	private LocalDate endDay;
}
