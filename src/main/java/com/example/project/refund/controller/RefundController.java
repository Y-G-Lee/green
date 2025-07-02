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

	    // (선택적) 반품한 주문 번호 조회 API
	    @GetMapping("/takeback")
	    public List<Integer> getRefundedOrderNos(@RequestParam("uId") String uId) {
//	        return refundService.findRefundedOrderNos(uId);
	    	 try {
	    	        List<Integer> refundedOrderNos = refundService.findRefundedOrderNos(uId);
	    	        System.out.println("반품된 주문번호 목록: " + refundedOrderNos);
	    	        return refundedOrderNos;
	    	    } catch (Exception e) {
	    	        e.printStackTrace();
	    	        throw e;  // 또는 원하는 예외처리
	    	    }
	    }
}
