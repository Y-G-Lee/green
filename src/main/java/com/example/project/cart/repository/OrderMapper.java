package com.example.project.cart.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.repository.query.Param;

import com.example.project.cart.dto.OrderDto;
import com.example.project.cart.dto.UpdateOrderDto;

@Mapper
public interface OrderMapper {
	void insertOrder(OrderDto orderDto);
	OrderDto selectOrderByUid(String uId);
	void updateMileage(UpdateOrderDto updateOrderDto);
	void deleteCart(String uId);
	int countGreenOrders();
	int countRemodeling();
	int countGreenOrdersInUp();
	void minusMileage(UpdateOrderDto updateOrderDto);
	void updatePoint(UpdateOrderDto updateOrderDto);
}
