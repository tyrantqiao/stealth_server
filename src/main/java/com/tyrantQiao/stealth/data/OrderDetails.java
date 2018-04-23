package com.tyrantQiao.stealth.data;

import java.time.Instant;
import java.time.LocalDate;
import java.util.Random;

/**
 * author: TyrantQiao
 * date: 2018/4/22
 * github: github.com/tyrantqiao
 */
public class OrderDetails {
	//TODO 购物车怎样实现

	private int orderChannel;
	private int payType;
	private int orderType;
	private Long orderId;

	//TODO make orderId Cache
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
}
