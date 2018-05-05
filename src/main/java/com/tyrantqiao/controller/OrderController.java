package com.tyrantqiao.controller;

import com.tyrantqiao.entity.Order;
import com.tyrantqiao.service.OrderService;
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
	private OrderService orderService;

	@Autowired
	public OrderController(OrderService orderService) {
		this.orderService = orderService;
	}

	/**
	 * 如果你不是用表格之类的申请，就必须用GET，因为游览器是这么处理的
	 */
	@GetMapping("/add")
	public void addOrder() {
		Order testAddOrder = new Order(1L, 111L);
		orderService.insert(testAddOrder);
	}

	@GetMapping("/get")
	public Order getOrderById(@RequestParam(name = "orderUserId") Long orderUserId) {
		return orderService.getByOrderUserId(orderUserId);
	}

	/**
	 * 因为我们没有发出delete的请求，而是通过service调用mapper删除，这个本质上只是{@code @GetMapping()}
	 * @param orderId
	 */
	@GetMapping("/delete")
	public void deleteOrderByOrderId(@RequestParam(name = "orderId") Long orderId) {
		orderService.deleteOrderByOrderId(orderId);
	}
}
