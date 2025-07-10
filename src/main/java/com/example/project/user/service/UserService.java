package com.example.project.user.service;

import java.util.List;

import org.springframework.security.core.GrantedAuthority;

import com.example.project.user.dto.CreateUserDto;
import com.example.project.user.dto.KakaoUserDto;
import com.example.project.user.dto.SignInDto;
import com.example.project.user.dto.UpdateUserDto;
import com.example.project.user.dto.UserBusinessRegistrationDto;
import com.example.project.user.dto.UserDto;
import com.example.project.user.dto.UserRoleDto;


public interface UserService {
	UserDto getUser(String id);
	void createUser(CreateUserDto createUserDto);
	String getEmailById(Long id);
	String createToken(SignInDto signInDto);
	UserDto kakaoLogin(KakaoUserDto kakaoUserDto);
	boolean checkPassword(UserDto userDto);
	void deleteUser(String id);
	void updateUserPassword(String id, UpdateUserDto updateUserDto);
	void updateUser(UpdateUserDto updateUserDto);
	UserDto getId(String email);
	void createUserRole(UserRoleDto userRoleDto);
	UserBusinessRegistrationDto findUserRegistration(String uId);
	void deleteBusiness(String uId);
	List<GrantedAuthority> getAuthoritiesByUserId(String uId);
}
