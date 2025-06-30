package com.example.project.registration.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.example.project.registration.dto.RegistrationDto;
import com.example.project.registration.service.RegistrationService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/api")
public class RegistrationController {

    private final RegistrationService service;

    @PostMapping("/registration")
    public ResponseEntity<?> uploadRegistrationImages(
            @RequestParam("registrationNum") String registrationNum,
            @RequestParam("files") List<MultipartFile> files) {

        String uploadDir = "C:/녹색제품 이미지/";

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null || !(auth.getPrincipal() instanceof UserDetails)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("인증 필요");
        }

        String userId = ((UserDetails) auth.getPrincipal()).getUsername();
        List<RegistrationDto> imageDtos = new ArrayList<>();
        int index = 1;

        try {
            for (MultipartFile file : files) {
                if (file.isEmpty()) continue;

                String ext = Optional.ofNullable(file.getOriginalFilename()).filter(f -> f.contains("."))
                        .map(f -> f.substring(f.lastIndexOf("."))).orElse(".jpg");
                String filename = registrationNum + "_" + index++ + ext;

                file.transferTo(new File(uploadDir + filename));

                RegistrationDto dto = new RegistrationDto();
                dto.setRegistrationNum(registrationNum);
                dto.setUId(userId);
                dto.setBImage(filename);
                imageDtos.add(dto);
            }

            for (RegistrationDto dto : imageDtos) {
                service.createRegistration(dto);
            }

            return ResponseEntity.ok("이미지 저장 완료");
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("파일 저장 실패");
        }
    }
    
	@PostMapping("/deleteRegistration")
	public ResponseEntity<Void> deleteRegistration(@RequestBody RegistrationDto dto) {
		String uId = dto.getUId(); 
		service.deleteRegistration(uId); 
		return ResponseEntity.ok().build();
	}
}
