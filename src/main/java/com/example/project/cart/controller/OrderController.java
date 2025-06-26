package com.example.project.cart.controller;

import java.util.List;
import java.util.Map;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.project.cart.service.OrderService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/order")
public class OrderController {
	private final OrderService orderService;
	
	@PostMapping("/complete")
	public String completeOrder(
	    @RequestBody Map<String, Object> requestData,
	    Authentication authentication
	) {
		String uId = ((UserDetails) authentication.getPrincipal()).getUsername();
	    String memo = (String) requestData.getOrDefault("memo", "");

	    List<String> pIdList = null;
	    Object rawPIdList = requestData.get("productId");
	    if (rawPIdList instanceof List<?>) {
	        pIdList = ((List<?>) rawPIdList).stream()
	            .filter(item -> item instanceof String)
	            .map(item -> (String) item)
	            .toList();
	    }
	    System.out.println(pIdList);

	    List<Integer> quantityList = null;
	    Object rawQuantityList = requestData.get("quantity");
	    if (rawQuantityList instanceof List<?>) {
	        quantityList = ((List<?>) rawQuantityList).stream()
	            .filter(item -> item instanceof Number)
	            .map(item -> ((Number) item).intValue())
	            .toList();
	    }

	    if (pIdList == null || pIdList.isEmpty()) {
	        return "상품 ID가 없습니다.";
	    }
	    if (quantityList == null || quantityList.size() != pIdList.size()) {
	        return "수량 정보가 올바르지 않습니다.";
	    }

	    orderService.completeMultiPayment(uId, pIdList, memo, quantityList);

	    return "결제가 완료되었습니다.";
	}
}
