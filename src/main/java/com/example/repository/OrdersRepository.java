package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.domain.Orders;

public interface OrdersRepository extends JpaRepository<Orders, Long>{

}
