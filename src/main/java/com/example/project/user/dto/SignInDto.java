package com.example.project.user.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class SignInDto {
	
	@NotNull
	@Size(min = 1, max = 40)
	private String id;
	
	@NotNull
	@Size(min = 1, max = 100)
	private String password;
}
