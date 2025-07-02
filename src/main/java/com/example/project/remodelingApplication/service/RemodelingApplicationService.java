package com.example.project.remodelingApplication.service;

import java.util.List;

import com.example.project.remodelingApplication.dto.RemodelingApplicationDto;

public interface RemodelingApplicationService {
	List<RemodelingApplicationDto> getAllList();
	List<RemodelingApplicationDto> getRemodeling(String uId);
}
