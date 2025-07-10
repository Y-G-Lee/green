package com.example.project.user.controller;

import java.util.Collections;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.project.config.helper.CookieHelper;
import com.example.project.config.jwt.TokenDto;
import com.example.project.config.jwt.TokenProvider;
import com.example.project.helper.LoginHelper;
import com.example.project.user.dto.KakaoUserDto;
import com.example.project.user.dto.SignInDto;
import com.example.project.user.dto.UserDto;
import com.example.project.user.repository.UserMapper;
import com.example.project.user.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class KakaoAuthController {

	private final UserMapper userMapper;
	private final PasswordEncoder passwordEncoder;
    private final UserService userService;
    private final TokenProvider tokenProvider;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final CookieHelper cookieHelper;
    private final LoginHelper loginHelper;

    @PostMapping("/kakao")
    public ResponseEntity<TokenDto> kakaoLogin(@RequestBody KakaoUserDto kakaoUserDto) {
        // 1. 유저 로그인 또는 회원가입 처리
        userService.checkKakaoUser(kakaoUserDto);
        
        SignInDto signInDto = SignInDto.builder().id(kakaoUserDto.getEmail()).password(kakaoUserDto.getEmail()).build();
        String token = userService.createToken(signInDto);

        // 쿠키에 토큰 추가
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Set-Cookie", cookieHelper.makeJwtCookie(token));

        return new ResponseEntity<>(TokenDto.builder().token(token).build(), httpHeaders, HttpStatus.OK);
    }
}