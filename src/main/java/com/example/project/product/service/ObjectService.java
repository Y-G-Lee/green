package com.example.project.product.service;

import java.util.List;

import com.example.project.product.dto.CreateProductDto;
import com.example.project.product.dto.FirstEnergyDto;
import com.example.project.product.dto.FirstGreenDto;
import com.example.project.product.dto.GreenObjectDto;
import com.example.project.product.dto.ProductImageDto;

public interface ObjectService {
	List<FirstGreenDto> getAllObjects();
	List<FirstEnergyDto> getAllEnergy();
	GreenObjectDto getId(String productId);
	void createObject(CreateProductDto createProductDto);
	List<CreateProductDto> findUser(String registrationNum);
	void saveImages(List<ProductImageDto> images);
}
