package com.example.project.orderList.service;

import java.util.List;
import org.springframework.stereotype.Service;
import com.example.project.orderList.dto.OrderListDto;
import com.example.project.orderList.repository.OrderListMappers;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderListServiceImpl implements OrderListService{
	
	private final OrderListMappers orderListMappers;
	
	@Override
	public List<OrderListDto> getOrder(String uId) {
		
		return orderListMappers.findOrder(uId);
	}

}
