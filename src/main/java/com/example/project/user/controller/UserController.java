package com.example.project.user.controller;

import java.net.URI;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.project.config.helper.CookieHelper;
import com.example.project.config.jwt.TokenDto;
import com.example.project.product.dto.CreateProductDto;
import com.example.project.user.dto.CreateUserDto;
import com.example.project.user.dto.SignInDto;
import com.example.project.user.dto.UpdateUserDto;
import com.example.project.user.dto.UserBusinessRegistrationDto;
import com.example.project.user.dto.UserDto;
import com.example.project.user.dto.UserRoleDto;
import com.example.project.user.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController {
	private final UserService userService;
	private final CookieHelper cookieHelper;
	private final PasswordEncoder passwordEncoder;

	@GetMapping("/check-userId")
	public ResponseEntity<Void> checkUserId(@RequestParam("id") String id) {
		System.out.println(id);
		UserDto user = userService.getUser(id);
		if (user != null) {
			return ResponseEntity.status(409).build();
		}
		return ResponseEntity.ok().build();
	}

	@GetMapping("/email")
	public ResponseEntity<String> getEmail(@RequestParam Long id) {
		String email = userService.getEmailById(id);
		return ResponseEntity.ok(email);
	}

	@PostMapping("/sign-up")
	public ResponseEntity<Void> signUp(@RequestBody CreateUserDto createUserDto) {
		userService.createUser(createUserDto);
		URI uri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/user/check-userId")
				.buildAndExpand(createUserDto.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}

	@PostMapping("/sign-in")
	public ResponseEntity<TokenDto> signIn(@RequestBody SignInDto signInDto) {
		String token = userService.createToken(signInDto);

		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.add("Set-Cookie", cookieHelper.makeJwtCookie(token));

		return new ResponseEntity<>(TokenDto.builder().token(token).build(), httpHeaders, HttpStatus.OK);
	}

	@PostMapping("/sign-out")
	public ResponseEntity<Void> signOut() {
		HttpHeaders httpHeaders = new HttpHeaders();
		cookieHelper.deleteJwtCookie(httpHeaders);
		return ResponseEntity.ok().headers(httpHeaders).body(null);
	}

	@PostMapping("/checkPassword")
	public boolean checkPassword(@RequestBody UserDto userDto) {
		UserDto user = userService.getUser(userDto.getId());
		return passwordEncoder.matches(userDto.getPassword(), user.getPassword());
	}

	@PostMapping("/byebye")
	public ResponseEntity<Void> deleteUser(@RequestBody UserDto userDto) {
		String id = userDto.getId();
		userService.deleteUser(id);
		HttpHeaders httpHeaders = new HttpHeaders();
		cookieHelper.deleteJwtCookie(httpHeaders);
		return ResponseEntity.ok().headers(httpHeaders).body(null);
	}

	@GetMapping("/userById")
	public ResponseEntity<UserDto> getUser(@RequestParam("id") String id) {
		UserDto user = userService.getUser(id);
		return ResponseEntity.ok(user);
	}

	@PatchMapping("/changePw")
	public ResponseEntity<Void> changePw(@RequestBody UpdateUserDto updateUserDto) {
		userService.updateUserPassword(updateUserDto.getId(), updateUserDto);
		return ResponseEntity.ok().build();
	}

	@PatchMapping("/update")
	public ResponseEntity<Void> updateUser(@RequestBody UpdateUserDto updateUserDto) {
		userService.updateUser(updateUserDto);
		return ResponseEntity.ok().build();
	}

	@GetMapping("/Id")
	public ResponseEntity<UserDto> GetId(@RequestParam("email") String email) {
		UserDto user = userService.getId(email);
		System.out.println("Retrieved User ID: " + (user != null ? user.getId() : "null"));
		return ResponseEntity.ok(user);
	}

	@PostMapping("/UserRole")
	public void userRole(@RequestBody UserRoleDto userRoleDto) {
		userService.createUserRole(userRoleDto);
	}

	@GetMapping("/findUserRegistration")
	public ResponseEntity<UserBusinessRegistrationDto> userRegistration(@RequestParam("uId") String uId) {
		UserBusinessRegistrationDto result = userService.findUserRegistration(uId);
		if (result == null)
			return ResponseEntity.notFound().build();
		return ResponseEntity.ok(result);
	}
	
	@PostMapping("/deleteBusiness")
	public ResponseEntity<Void> deleteBusiness(@RequestBody UserDto userDto) {
		String uId = userDto.getId();
		userService.deleteBusiness(uId);
		return ResponseEntity.ok().build();
	}	
}
