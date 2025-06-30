package com.example.project.product.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.project.product.dto.CreateProductDto;
import com.example.project.product.dto.FirstEnergyDto;
import com.example.project.product.dto.FirstGreenDto;
import com.example.project.product.dto.GreenObjectDto;
import com.example.project.product.dto.ProductImageDto;
import com.example.project.product.repository.ObjectMappers;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ServiceImpl implements ObjectService {

	private final ObjectMappers mapper;

	@Override
	public List<FirstGreenDto> getAllObjects() {
		return mapper.findAllObjects();
	}

	@Override
	public List<FirstEnergyDto> getAllEnergy() {
		return mapper.findAllEnergy();
	}

	@Override
	public GreenObjectDto getId(String productId) {
		GreenObjectDto product = mapper.findId(productId);
		List<String> images = mapper.findImagesByProductId(productId);
		product.setImages(images);
		return product;
	}

	@Override
	public void createObject(CreateProductDto createProductDto) {
		mapper.createObject(createProductDto);

	}

	@Override
	public List<CreateProductDto> findUser(String registrationNum) {
		return mapper.findUserProduct(registrationNum);
	}
	
	@Transactional
	@Override
	public void saveImages(List<ProductImageDto> images) {
		for (ProductImageDto image : images) {
			mapper.insertImage(image);
		}
	}
}
