package com.example.project.cart.service;

import java.util.List;

import com.example.project.cart.dto.UpdateOrderDto;

public interface OrderService {
	void completeSinglePayment(String uId, String pId, String memo, int quantity,String address, String detailAddress, int mileage);
	void completeMultiPayment(String uId, List<String> pIdList, String memo, List<Integer> quantity,String address, String detailAddress, int mileage);
	void updateMileage(String uId, UpdateOrderDto updateOrderDto);
	void deleteCart(String uId);
	int countGreenOrders();
	int countRemodeling();
	int countGreenOrdersInUp();
	void minusMileage(String uId, UpdateOrderDto updateOrderDto);
	void updatePoint(String uId, UpdateOrderDto updateOrderDto);
}
