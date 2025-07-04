package com.example.project.refund.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.project.refund.dto.RefundDto;

@Mapper
public interface RefundMappers {

	void insertRefund(RefundDto refundDto);

	List<Integer> findRefundedOrderNos(@Param("uId") String uId);

}
