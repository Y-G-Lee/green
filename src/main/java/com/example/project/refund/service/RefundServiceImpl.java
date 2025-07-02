package com.example.project.refund.service;

import java.util.List;

import org.springframework.stereotype.Service;
import com.example.project.refund.dto.RefundDto;
import com.example.project.refund.repository.RefundMappers;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RefundServiceImpl implements RefundService {

	  private final RefundMappers refundMappers;

	  @Override
	  @Transactional
	  public void insertRefund(RefundDto refundDto) {
	      try {
	          System.out.println("insertRefund: " + refundDto);
	          refundMappers.insertRefund(refundDto);
	          System.out.println("INSERT 성공");
	      } catch (Exception e) {
	          System.out.println("INSERT 실패!");
	          e.printStackTrace();  // 여기에 실제 오류가 찍힐 것
	          throw e; // Controller로 예외 전파
	      }
	  }

	    @Override
	    public List<Integer> findRefundedOrderNos(String uId) {
	        System.out.println("findRefundedOrderNos: " + uId);
	        return refundMappers.findRefundedOrderNos(uId);
	    }

}
