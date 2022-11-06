package com.example.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import lombok.Data;

@Entity
@Data
public class Items {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long itemId;
	@OneToOne
	private Orders order;
	private String itemName;
	private Double itemUnitPrice;
	private int itemQuantity;
}
