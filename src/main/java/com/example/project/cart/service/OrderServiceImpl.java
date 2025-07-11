package com.example.project.cart.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.project.cart.dto.CartDto;
import com.example.project.cart.dto.OrderDto;
import com.example.project.cart.dto.UpdateOrderDto;
import com.example.project.cart.repository.CartMappers;
import com.example.project.cart.repository.OrderMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService{
	private final OrderMapper orderMapper;
	private final CartMappers cartMapper;
	
	@Override
	public void completeSinglePayment(String uId, String pId, String memo, int quantity, String address, String detailAddress, int mileage) {
		System.out.println(uId);
		System.out.println(pId);
		System.out.println(memo);
		System.out.println(quantity);
		List<CartDto> pendingCarts = cartMapper.selectPendingCartByUser(uId);
		System.out.println(pendingCarts);
		
		for(CartDto cart : pendingCarts) {
			if(cart.getProductId() != null && cart.getProductId().equals(pId)) {
				OrderDto orderDto = new OrderDto();
				orderDto.setUId(uId);
				orderDto.setPId(pId);
				orderDto.setQuantity(quantity);
				orderDto.setMemo(memo);
				orderDto.setAddress(address);
				orderDto.setDetailAddress(detailAddress);
				orderDto.setMileage(mileage);
				
				orderMapper.insertOrder(orderDto);
				cartMapper.updateCheckBuyToTrue(uId, pId);
			}
		}
		
	}

	@Override
	public void completeMultiPayment(String uId, List<String> pIdList, String memo, List<Integer> quantityList, String address, String detailAddress, int mileage) {
		List<CartDto> pendingCarts = cartMapper.selectPendingCartByUser(uId);

		for(int i=0; i<pIdList.size(); i++) {
	        OrderDto orderDto = new OrderDto();
	        orderDto.setUId(uId);
	        orderDto.setPId(pIdList.get(i));
	        orderDto.setQuantity(quantityList.get(i));
	        orderDto.setMemo(memo);
	        orderDto.setAddress(address);
	        orderDto.setDetailAddress(detailAddress);
	        orderDto.setMileage(mileage);
	        
	        orderMapper.insertOrder(orderDto);
	        cartMapper.updateCheckBuyToTrue(uId, pIdList.get(i));
	    }
	}

	@Override
	public void updateMileage(String uId, UpdateOrderDto updateOrderDto) {
		orderMapper.updateMileage(updateOrderDto);
	}

	@Override
	public void deleteCart(String uId) {
		orderMapper.deleteCart(uId);
	}
	
	public int countGreenOrders() {
		return orderMapper.countGreenOrders();
	}

	@Override
	public int countRemodeling() {
		return orderMapper.countRemodeling();
	}

	@Override
	public int countGreenOrdersInUp() {
		return orderMapper.countGreenOrdersInUp();
	}

	@Override
	public void minusMileage(String uId, UpdateOrderDto updateOrderDto) {
		orderMapper.minusMileage(updateOrderDto);
	}

	@Override
	public void updatePoint(String uId, UpdateOrderDto updateOrderDto) {
		orderMapper.updatePoint(updateOrderDto);
		
	}
}
