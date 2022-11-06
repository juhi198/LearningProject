package com.example.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.domain.Items;
import com.example.domain.Orders;
import com.example.dto.OrderCreationDto;
import com.example.repository.ItemsRepository;
import com.example.repository.OrdersRepository;

@Service
public class OrderService {
	
	@Autowired
	OrdersRepository ordersRepo;
	
	@Autowired
	ItemsRepository itemsRepo;

	public Orders createOrder(OrderCreationDto orderDto) {
		Orders order=new Orders();
		order.setOrderDate(orderDto.getOrderDate());
		order.setOrderStatus("New");
		List<Items> itemList=new ArrayList<>();
		orderDto.getItems().forEach(itm->{
			Items currentItem=new Items();
			currentItem.setItemName(itm.getItemName());
			currentItem.setItemUnitPrice(itm.getItemUnitPrice());
			currentItem.setItemQuantity(itm.getItemQuantity());
//			currentItem.setOrder(order);
			//itemsRepo.save(currentItem);
			itemList.add(currentItem);
		});
		order.setItems(itemList);
		
		itemsRepo.saveAll(itemList);

		Orders data = ordersRepo.save(order);
		if(data!=null) {
			return data;
		}
		return null;
	}

	public Orders getOrdersById(Long orderId) {
		Optional<Orders> orderData = ordersRepo.findById(orderId);
		if(orderData.isPresent()) {
			return orderData.get();
		}
		return null;
	}

	public List<Orders> getAllOrders() {
		return ordersRepo.findAll();
	}

}
