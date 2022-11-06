package com.example.dto;

import java.time.LocalDateTime;
import java.util.List;

import lombok.Data;

@Data
public class OrderCreationDto {

	private LocalDateTime orderDate;
	private List<ItemDto> items;
}
