package com.example.project.orderList.service;

import java.util.List;
import com.example.project.orderList.dto.OrderListDto;

public interface OrderListService {
	
	List <OrderListDto> getOrder(String uId);
	
}
