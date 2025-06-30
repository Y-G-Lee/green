package com.example.project.cart.service;

import java.util.List;

public interface OrderService {
	void completeSinglePayment(String uId, String pId, String memo, int quantity,String address, String detailAddress);
	void completeMultiPayment(String uId, List<String> pIdList, String memo, List<Integer> quantity,String address, String detailAddress);
}
