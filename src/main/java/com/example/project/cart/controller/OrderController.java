package com.example.project.cart.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.project.cart.dto.UpdateOrderDto;
import com.example.project.cart.service.OrderService;

import jakarta.mail.search.IntegerComparisonTerm;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/order")
public class OrderController {
	private final OrderService orderService;

	@PostMapping("/complete")
	public String completeOrder(@RequestBody Map<String, Object> requestData, Authentication authentication) {
		String uId = ((UserDetails) authentication.getPrincipal()).getUsername();
		String memo = (String) requestData.getOrDefault("memo", "");
		String address = (String) requestData.getOrDefault("address", "");
		String detailAddress = (String) requestData.getOrDefault("detailAddress", "");
		int mileage = Integer.parseInt(String.valueOf(requestData.getOrDefault("mileage", 0)));

		List<String> pIdList = null;
		Object rawPIdList = requestData.get("productId");
		if (rawPIdList instanceof List<?>) {
			pIdList = ((List<?>) rawPIdList).stream().filter(item -> item instanceof String).map(item -> (String) item)
					.toList();
		}
		System.out.println(pIdList);

		List<Integer> quantityList = null;
		Object rawQuantityList = requestData.get("quantity");
		if (rawQuantityList instanceof List<?>) {
			quantityList = ((List<?>) rawQuantityList).stream().filter(item -> item instanceof Number)
					.map(item -> ((Number) item).intValue()).toList();
		}

		if (pIdList == null || pIdList.isEmpty()) {
			return "상품 ID가 없습니다.";
		}
		if (quantityList == null || quantityList.size() != pIdList.size()) {
			return "수량 정보가 올바르지 않습니다.";
		}

		orderService.completeMultiPayment(uId, pIdList, memo, quantityList, address, detailAddress, mileage);
		return "결제가 완료되었습니다.";
	}

	@PatchMapping("/saveMileage")
	public ResponseEntity<Void> updateMileage(@RequestBody UpdateOrderDto updateOrderDto) {
		orderService.updateMileage(updateOrderDto.getUId(), updateOrderDto);
		return ResponseEntity.ok().build();
	}

	@PostMapping("/delete")
	public ResponseEntity<Void> deleteCart(@RequestBody Map<String, String> payload) {
		String uId = payload.get("uId");
		orderService.deleteCart(uId);
		return ResponseEntity.ok().build();
	}

	@GetMapping("/countOrder")
	public int greenCountOrder() {
		return orderService.countGreenOrders();
	}

	@GetMapping("/countRemodeling")
	public int greenRemodeling() {
		return orderService.countRemodeling();
	}

	@GetMapping("/countOrderInUp")
	public int greenCountOrderInUp() {
		return orderService.countGreenOrdersInUp();
	}

	@PatchMapping("/minusMileage")
	public ResponseEntity<Void> minusMileage(@RequestBody UpdateOrderDto updateOrderDto) {
		orderService.minusMileage(updateOrderDto.getUId(), updateOrderDto);
		return ResponseEntity.ok().build();
	}

	@PatchMapping("/plusMileage")
	public ResponseEntity<Void> updatePoint(@RequestBody UpdateOrderDto updateOrderDto) {
		orderService.updatePoint(updateOrderDto.getUId(), updateOrderDto);
		return ResponseEntity.ok().build();
	}
}
