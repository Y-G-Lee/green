package com.example.project.product.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.project.product.dto.CreateProductDto;
import com.example.project.product.dto.FirstEnergyDto;
import com.example.project.product.dto.FirstGreenDto;
import com.example.project.product.dto.GreenObjectDto;
import com.example.project.product.dto.ProductImageDto;

@Mapper
public interface ObjectMappers {
	List<FirstGreenDto> findAllObjects();
	List<FirstEnergyDto> findAllEnergy();
	List<String> findImagesByProductId(String productId);
	GreenObjectDto findId(String productId);
	void createObject (CreateProductDto createProductDto);
	List<CreateProductDto> findUserProduct(String registrationNum); 
	void insertImage(ProductImageDto image);
}
