package com.example.project.orderList.repository;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import com.example.project.orderList.dto.OrderListDto;

@Mapper
public interface OrderListMappers {
	
	List <OrderListDto> findOrder(String uId);
}
