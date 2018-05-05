package com.tyrantqiao.service;

import com.tyrantqiao.entity.Order;
import com.tyrantqiao.entity.User;
import com.tyrantqiao.mapper.OrderMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

/**
 * date: 2018/4/17
 * github: github.com/tyrantqiao
 *
 * @author TyrantQiao
 */
@Service
@CacheConfig(cacheNames = "orders")
public class OrderService {
	private static final Logger LOGGER = LoggerFactory.getLogger(OrderService.class);

	private OrderMapper orderMapper;
	private RedisTemplate<String, User> redisTemplate;

	@Autowired
	public OrderService(OrderMapper orderMapper, RedisTemplate<String, User> redisTemplate) {
		this.orderMapper = orderMapper;
		this.redisTemplate = redisTemplate;
	}


	@CacheEvict(cacheNames = "order1", key = "#orderId")
	public void deleteOrderByOrderId(Long orderId) {
		orderMapper.deleteByOrderId(orderId);
		LOGGER.info("delete order: " + orderId);
	}

	@CachePut(cacheNames = "order1", key = "#order.orderId")
	public void insert(Order order) {
		orderMapper.insertOrder(order);
		LOGGER.info(order.toString());
	}

	@Cacheable(cacheNames = "order1", key = "#orderUserId")
	public Order getByOrderUserId(Long orderUserId) {
		Order getOrder = orderMapper.getByOrderUserId(orderUserId);
		LOGGER.info(getOrder.toString());
		return getOrder;
	}
}
