package com.tyrantqiao.controller;

import com.tyrantqiao.entity.Order;
import com.tyrantqiao.mapper.OrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Description:
 *
 * @author tyrantqiao [tyrantqiao@gmail.com]
 * @see <a href="https://github.com/tyrantqiao">qiao's github</a>
 */
@RestController
@RequestMapping(value = "/order")
public class OrderController {
	private OrderMapper orderMapper;

	@Autowired
	public OrderController(OrderMapper orderMapper) {
		this.orderMapper = orderMapper;
	}

	/**
	 * 如果你不是用表格之类的申请，就必须用GET，因为游览器是这么处理的
	 */
	@GetMapping("/add")
	public void addOrder() {
		Order testAddOrder = new Order(1L, 111L);
		orderMapper.insertOrder(testAddOrder);
	}

	@GetMapping("/get")
	public Order getOrderById(@RequestParam(name = "orderUserId") Long orderUserId) {
		return orderMapper.getByOrderUserId(orderUserId);
	}
}
