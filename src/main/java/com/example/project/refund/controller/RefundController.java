package com.example.project.refund.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.example.project.refund.dto.RefundDto;
import com.example.project.refund.service.RefundService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class RefundController {

	private final RefundService refundService;

	@PostMapping("/takeback")
	public void createRefund(@RequestBody RefundDto refundDto) {
		refundService.insertRefund(refundDto);
	}

	@GetMapping("/takeback")
	public List<Integer> getRefundedOrderNos(@RequestParam("uId") String uId) {
		try {
			List<Integer> refundedOrderNos = refundService.findRefundedOrderNos(uId);
			System.out.println("반품된 주문번호 목록: " + refundedOrderNos);
			return refundedOrderNos;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
}
