package com.example.project.user.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.project.user.dto.CreateUserDto;
import com.example.project.user.dto.UpdateUserDto;
import com.example.project.user.dto.UserBusinessRegistrationDto;
import com.example.project.user.dto.UserDto;
import com.example.project.user.dto.UserRoleDto;

@Mapper
public interface UserMapper {
	 UserDto findUserById(@Param("id") String id);
	 List<String> findAuthoritiesByUserId(@Param("u_id") String u_id);
	 int saveUser(CreateUserDto createUserDto);
	 void saveUserAuthority(CreateUserDto createUserDto);
	 int saveAdminAuthority(CreateUserDto createUserDto);
	 String findEmailByUserId(Long id);
	 
	 UserDto findByEmail(String email);
	 void insertKakaoUser(CreateUserDto createUserDto);
	 void saveKakaoUser(CreateUserDto createUserDto);
	 void saveUserKakaoAuthority(CreateUserDto createUserDto);
	 
	 String findPasswordById(String uId);
	 void deleteAddress(@Param("id") String id);
	 void deleteRole(@Param("id") String id);
	 void deleteCart(@Param("id") String id);
	 void deleteBusinessAcception(@Param("id") String id);
	 void deleteRemodelingResult(@Param("id") String id);
	 void deleteAuthenticationCode(@Param("id") String id);
	 void deleteBusinessRegistration(@Param("id") String id);
	 void deleteReview(@Param("id") String id);
	 void deleteReviewImage(@Param("id") String id);
	 void deleteOrder(@Param("id") String id);
	 void deleteRefund(@Param("id") String id);
	 void deleteProduct(@Param("id") String id);
	 void deleteProductImage(@Param("id") String id);
	 void deleteUser(@Param("id") String id);
	 
	 void updatePw(UpdateUserDto updateUserDto);
	 void updateEmail(UpdateUserDto updateUserDto);
	 UserDto findId(@Param("email") String email);
	 
	 void saveRole(UserRoleDto userRoleDto);
	 UserBusinessRegistrationDto findImage(@Param("uId") String uId);
	 void deleteRoleBusiness(@Param("uId") String uId);
}