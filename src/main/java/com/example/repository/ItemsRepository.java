package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.domain.Items;

public interface ItemsRepository extends JpaRepository<Items, Long> {

}
