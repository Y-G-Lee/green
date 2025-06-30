package com.example.project.orderList.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.example.project.orderList.dto.OrderListDto;

public interface OrderListService {
	
	List <OrderListDto> getOrder(@Param("uId") String uId);
	
}
