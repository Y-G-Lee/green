package com.example.project.cart.repository;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.repository.query.Param;

import com.example.project.cart.dto.OrderDto;

@Mapper
public interface OrderMapper {
	void insertOrder(OrderDto orderDto);
	OrderDto selectOrderByUid(String uId);
}
