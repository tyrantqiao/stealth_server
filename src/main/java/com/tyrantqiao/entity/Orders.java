package com.tyrantqiao.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * date: 2018/4/17
 * github: github.com/tyrantqiao
 *
 * @author tyrantqiao
 */
@Entity
@Table(name = "orders")

public class Orders implements Serializable {
	private static final long serialVersionUID = 1L;
	/**
	 * orderChannel(1)+PayType(1)+orderType(1)+Date-Month-day(4)+Unix-date(4)+Random(1)+id(4) = 16
	 */
	@Id
	@Column(name = "order_id", unique = true, length = 16)
	private Long orderId;

	@Column(name = "order_user_id", length = 8)
	private Long orderUserId;

	public Orders(Long orderId, Long orderUserId) {
		this.orderId = orderId;
		this.orderUserId = orderUserId;
	}

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public Long getOrderUserId() {
		return orderUserId;
	}

	public void setOrderUserId(Long orderUserId) {
		this.orderUserId = orderUserId;
	}
}
