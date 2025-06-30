package com.example.project.orderList.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.project.orderList.dto.OrderListDto;
import com.example.project.orderList.service.OrderListService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/api")
public class OrderListController {
	
	private final OrderListService orderListService;
	
	@GetMapping("/order-detail")
	public List<OrderListDto> getOrder(@RequestParam("uId") String uId) {
		return orderListService.getOrder(uId);
	}

}
