package com.tyrantQiao.stealth.POJO;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Random;

/**
 * author: TyrantQiao
 * date: 2018/4/17
 * github: github.com/tyrantqiao
 */
@Entity
public class Order {
	/**
	 * orderChannel(1)+PayType(1)+orderType(1)+Date-Month-day(4)+Unix-date(4)+Random(1)+id(4) = 16
	 */
	@Id
	@Column(name = "order_id", unique = true, length = 16)
	private Long orderId;

	@Column(name = "order_user_id", length = 8)
	private Long orderUserId;

	//TODO 购物车怎样实现
//	private

	private int orderChannel;
	private int payType;
	private int orderType;

	public Long getOrderId() {
		return orderId;
	}

	//TODO make orderId Intelligent
	public void setOrderId() {
		long unixTimeStamp = Instant.now().getEpochSecond();
		int dayOfMonth = LocalDate.now().getDayOfMonth();

		String stringBuffer = String.valueOf(getOrderChannel()) +
				getPayType() +
				getOrderType() +
				dayOfMonth +
				Long.toString(unixTimeStamp).substring(6) +
				new Random().nextInt(10);
//				Long.toString(getUser().getId()).substring();
		this.orderId = Long.getLong(stringBuffer);
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public int getOrderChannel() {
		return orderChannel;
	}

	public void setOrderChannel(int orderChannel) {
		this.orderChannel = orderChannel;
	}

	public int getPayType() {
		return payType;
	}

	public void setPayType(int payType) {
		this.payType = payType;
	}

	public int getOrderType() {
		return orderType;
	}

	public void setOrderType(int orderType) {
		this.orderType = orderType;
	}

	public Long getOrderUserId() {
		return orderUserId;
	}

	public void setUser(Long userId) {
		this.orderUserId = userId;
	}
}
