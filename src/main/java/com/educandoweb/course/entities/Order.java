package com.educandoweb.course.entities;

import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

import com.educandoweb.course.entities.enums.OrderStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Entity
@EqualsAndHashCode
@Table(name = "tb_order")
public class Order implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Getter @Setter
	private Long id;

	@JsonFormat(shape = Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "GMT")
	@Getter @Setter
	private Instant moment;

	private Integer orderStatus;

	@ManyToOne
	@JoinColumn(name = "client_id")
	@Getter @Setter
	private User client;
	
	@OneToMany(mappedBy = "id.order")
	@Getter
	private Set<OrderItem> items = new HashSet<>();

	public Order(Long id, Instant moment, OrderStatus orderStatus, User client) {
		super();
		this.id = id;
		this.moment = moment;
		setOrderStatus(orderStatus);
		this.client = client;
	}

	public OrderStatus getOrderStatus() {
		return OrderStatus.valueOf(orderStatus);
	}
	
	public void setOrderStatus(OrderStatus orderStatus) {
		if(orderStatus != null) {
			this.orderStatus = orderStatus.getCode();
		}
	}

	public double total() {
		return 0.0;
	}

}
