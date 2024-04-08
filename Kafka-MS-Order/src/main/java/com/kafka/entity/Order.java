package com.kafka.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "orders")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {

	@Id
	@GeneratedValue
	private int orderId;
	
	@Column
	private String orderNumber;
	
	@Column
	private String orderLocation;
	
	@Column
	private LocalDateTime orderDateTime;
	
	@Column
	private String creditCardNumber;
	
	@OneToMany(mappedBy = "order")
	private List<OrderItem> items;

}
