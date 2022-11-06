package com.example.controller;

import com.example.domain.Orders;
import com.example.service.OrderService;
import com.example.util.UrlMappings;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

import static com.example.util.UrlMappings.GET_ORDERS_BY_ID;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@ExtendWith(MockitoExtension.class)
class OrderControllerTest {

    @InjectMocks
    OrderController orderController;

    private MockMvc mockMvc;

    @Mock
    OrderService orderService;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(orderController).build();

    }

    @Test
    void createOrder() throws Exception {
        mockMvc.perform(post(UrlMappings.CREATE_ORDER));
    }

    @Test
    void getOrdersById() throws Exception {
        Mockito.when(orderService.getOrdersById(Mockito.any())).thenReturn(new Orders("COMPLETED"));
      ResultActions resultActions= mockMvc.perform(get(GET_ORDERS_BY_ID+"?orderId=1"));
    }
    @Test
    void getOrdersByIdNull() throws Exception {
        Mockito.when(orderService.getOrdersById(Mockito.any())).thenReturn(null);
        ResultActions resultActions= mockMvc.perform(get(GET_ORDERS_BY_ID+"?orderId=1"));
    }

    private static Orders getOrders() {
        Orders orders= new Orders("new");
        orders.setOrderDate(LocalDateTime.now());
        return orders;
    }
    @Test
    void getAllOrders() throws Exception {
        List<Orders> ordersList= Collections.singletonList(getOrders());
        Mockito.when(orderService.getAllOrders()).thenReturn(ordersList);
        mockMvc.perform(get(UrlMappings.GET_ALL_ORDERS));
    }

}