package com.kafka.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "order_items")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderItem {

	@Id
	@GeneratedValue
	private int orderItemId;
	
	@Column
	private String itemName;

	@Column
	private int price;
	
	@Column
	private int quantity;
	
	@JoinColumn(name = "order_id")
	@ManyToOne
	private Order order;
}
