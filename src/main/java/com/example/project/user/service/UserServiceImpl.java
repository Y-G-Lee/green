package com.example.project.user.service;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.project.config.exception.AlreadyExistedUserException;
import com.example.project.config.jwt.TokenProvider;
import com.example.project.config.property.ErrorMessagePropertySource;
import com.example.project.helper.LoginHelper;
import com.example.project.user.dto.CreateUserDto;
import com.example.project.user.dto.KakaoUserDto;
import com.example.project.user.dto.SignInDto;
import com.example.project.user.dto.UpdateUserDto;
import com.example.project.user.dto.UserBusinessRegistrationDto;
import com.example.project.user.dto.UserDto;
import com.example.project.user.dto.UserRoleDto;
import com.example.project.user.repository.UserMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
	private final UserMapper userMapper;
	private final PasswordEncoder passwordEncoder;
	private final ErrorMessagePropertySource errorMessagePropertySource;
	private final AuthenticationManagerBuilder authenticationManagerBuilder;
	private final TokenProvider tokenProvider;
	private final LoginHelper loginHelper;

	@Override
	public UserDto getUser(String id) {
		return userMapper.findUserById(id);
	}

	@Override
	public void createUser(CreateUserDto createUserDto) {
		log.info("회원가입 요청 id={}", createUserDto.getId());
		UserDto user = getUser(createUserDto.getId());
		if (user != null) {
			throw new AlreadyExistedUserException(errorMessagePropertySource.getAlreadyExistedUser());
		}

		createUserDto.setPassword(passwordEncoder.encode(createUserDto.getPassword()));
		userMapper.saveUser(createUserDto);
		userMapper.saveUserAuthority(createUserDto);
	}

	@Override
	public String createToken(SignInDto signInDto) {
		try {
			UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
					signInDto.getId(), signInDto.getPassword());

			Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);

			return tokenProvider.createToken(authentication);
		} catch (Exception ex) {
			throw new BadCredentialsException(errorMessagePropertySource.getBadCredentials());
		}

	}

	@Override
	public void checkKakaoUser(KakaoUserDto kakoUserService) {
		UserDto user = userMapper.findByEmail(kakoUserService.getEmail());
		if (user == null) {
			CreateUserDto newUser = new CreateUserDto();
			newUser.setId(kakoUserService.getEmail());
			newUser.setEmail(kakoUserService.getEmail());
			newUser.setPassword(passwordEncoder.encode(kakoUserService.getEmail()));

			userMapper.saveKakaoUser(newUser);
			userMapper.saveUserAuthority(newUser);	
		} else {
			// throw new IllegalStateException("이미 가입된 유저는 카카오 로그인 전용 인증 처리 필요");
			System.out.println("카카오 이미 있음");
			System.out.println(user);
		}
	}


	@Override
	public boolean checkPassword(UserDto userDto) {

		String savedPassword = userMapper.findPasswordById(userDto.getId());
		boolean matches = passwordEncoder.matches(userDto.getPassword(), savedPassword);
		return matches;
	}

	@Transactional
	@Override
	public void deleteUser(String id) {
		userMapper.deleteRole(id); 
		userMapper.deleteCart(id);
		userMapper.deleteAddress(id);
		userMapper.deleteRemodelingResult(id);
		userMapper.deleteBusinessRegistration(id);
		userMapper.deleteBusinessAcception(id);
		userMapper.deleteRefund(id);
		userMapper.deleteOrder(id);
		userMapper.deleteReviewImage(id);
		userMapper.deleteReview(id);
		userMapper.deleteAuthenticationCode(id);
		userMapper.deleteProductImage(id);
		userMapper.deleteProduct(id);
		userMapper.deleteUser(id);
	}

	@Override
	public void updateUserPassword(String id, UpdateUserDto updateUserDto) {
		updateUserDto.setId(id);
		updateUserDto.setPassword(passwordEncoder.encode(updateUserDto.getPassword()));
		userMapper.updatePw(updateUserDto);
	}

	@Override
	public void updateUser(UpdateUserDto updateUserDto) {
		if (updateUserDto.getPassword() != null && !updateUserDto.getPassword().isEmpty()) {
			updateUserDto.setPassword(passwordEncoder.encode(updateUserDto.getPassword()));
			userMapper.updatePw(updateUserDto);
		}

		if (updateUserDto.getEmail() != null && !updateUserDto.getEmail().isEmpty()) {
			userMapper.updateEmail(updateUserDto);
		}
	}

	@Override
	public UserDto getId(String email) {
		return userMapper.findId(email);
	}

	@Override
	public void createUserRole(UserRoleDto userRoleDto) {
		userMapper.saveRole(userRoleDto);
	}

	@Override
	public UserBusinessRegistrationDto findUserRegistration(String uId) {
		return userMapper.findImage(uId);
	}

	@Override
	public String getEmailById(Long id) {
		return userMapper.findEmailByUserId(id);
	}

	@Override
	public void deleteBusiness(String uId) {
		userMapper.deleteRoleBusiness(uId);
		
	}

}