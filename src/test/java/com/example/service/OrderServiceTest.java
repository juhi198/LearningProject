package com.example.service;

import com.example.domain.Orders;
import com.example.dto.ItemDto;
import com.example.dto.OrderCreationDto;
import com.example.repository.ItemsRepository;
import com.example.repository.OrdersRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
class OrderServiceTest {

    @InjectMocks
    OrderService orderService;
    @Mock
    OrdersRepository ordersRepo;

   @Mock
    ItemsRepository itemsRepo;

    @Test
    void createOrder() {
        OrderCreationDto dto= new OrderCreationDto();
        dto.setOrderDate(LocalDateTime.now());
        ItemDto itemDto= new ItemDto("Item1",12.4,12);
        dto.setItems(Collections.singletonList(itemDto));
        Mockito.when(itemsRepo.saveAll(Mockito.any())).thenReturn(null);
        Mockito.when(ordersRepo.save(Mockito.any())).thenReturn(new Orders("new"));
       Orders orders= orderService.createOrder(dto);
        Assertions.assertTrue(orders.getOrderStatus().equalsIgnoreCase("new"));
    }
    @Test
    void createOrderNull() {
        OrderCreationDto dto= new OrderCreationDto();
        dto.setOrderDate(LocalDateTime.now());
        ItemDto itemDto= new ItemDto("Item1",12.4,12);
        dto.setItems(Collections.singletonList(itemDto));
        Mockito.when(itemsRepo.saveAll(Mockito.any())).thenReturn(null);
        Mockito.when(ordersRepo.save(Mockito.any())).thenReturn(null);
        Orders orders= orderService.createOrder(dto);
        assertNull(orders);
    }

    @Test
    void getOrdersById() {
        //	Optional<Orders> orderData = ordersRepo.findById(orderId);
        Orders orders = getOrders();
        Optional<Orders> optionalOrders= Optional.of(orders);
        Mockito.when(ordersRepo.findById(Mockito.any())).thenReturn(optionalOrders);
       Orders orders1= orderService.getOrdersById(2L);
       Assertions.assertTrue(orders1.getOrderStatus().equalsIgnoreCase("NEW"));
    }
    @Test
    void getOrdersByIdNull() {
        Optional<Orders> optionalOrders= Optional.empty();
        Mockito.when(ordersRepo.findById(Mockito.any())).thenReturn(optionalOrders);
        Orders orders1= orderService.getOrdersById(4L);
        Assertions.assertNull(orders1);
    }

    private static Orders getOrders() {
        Orders orders= new Orders("new");
        orders.setOrderDate(LocalDateTime.now());
        return orders;
    }

    @Test
    void getAllOrders() {
        List<Orders> ordersList=Collections.singletonList(getOrders());
        Mockito.when(ordersRepo.findAll()).thenReturn(ordersList);
      List<Orders> orders=  orderService.getAllOrders();
      Assertions.assertTrue(orders.size()>0);

    }
}