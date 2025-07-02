package com.example.project.refund.service;

import java.util.List;

import com.example.project.refund.dto.RefundDto;

public interface RefundService {

	void insertRefund(RefundDto refundDto);

	List<Integer> findRefundedOrderNos(String uId);

}
