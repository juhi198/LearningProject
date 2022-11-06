package com.example.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.domain.Orders;
import com.example.dto.OrderCreationDto;
import com.example.service.OrderService;
import com.example.util.ConstantUtility;
import com.example.util.ResponseHandler;
import com.example.util.UrlMappings;

@RestController
public class OrderController {
	
	@Autowired
	OrderService orderService;
	

	@PostMapping(UrlMappings.CREATE_ORDER)
	public ResponseEntity<Object> createOrder(@RequestBody OrderCreationDto orderDto) {
		Orders result = orderService.createOrder(orderDto);
		if (result!=null)
				 {
			return ResponseHandler.generateResponse(HttpStatus.OK, true, ConstantUtility.SUCCESS, result);

		}
		else {
			return ResponseHandler.generateResponse(HttpStatus.EXPECTATION_FAILED, false, ConstantUtility.FAILURE,
					result);
		}
	}
	@GetMapping(UrlMappings.GET_ORDERS_BY_ID)
	public ResponseEntity<Object> getOrdersById(@RequestParam Long orderId) {
		Orders response = orderService.getOrdersById(orderId);
		if (response!=null) {
			return ResponseHandler.generateResponse(HttpStatus.OK, true, ConstantUtility.DATA_FETCHED_SUCCESSFULLY, response);
		}
		else {
			return ResponseHandler.generateResponse(HttpStatus.NO_CONTENT, false, ConstantUtility.FAILURE, response);
		}
	}
	
	@GetMapping(UrlMappings.GET_ALL_ORDERS)
	public ResponseEntity<Object> getAllOrders() {
		List<Orders> response = orderService.getAllOrders();
			return ResponseHandler.generateResponse(HttpStatus.OK, true, ConstantUtility.DATA_FETCHED_SUCCESSFULLY, response);
	}

	
	
}
