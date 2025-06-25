package com.example.project.cart.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.project.cart.dto.CartDto;
import com.example.project.cart.dto.CreateCart;

@Mapper
public interface CartMappers {
	void createCart(CreateCart createCart);
	List<CartDto> findCart(String uId);
	
	void updateCheckBuyToTrue(@Param("uId") String uId, @Param("productId") String productId);
	List<CartDto> selectPendingCartByUser(String uId);
}
