package com.tyrantqiao.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.tyrantqiao.entity.Order;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

/**
 * date: 2018/4/19
 * github: github.com/tyrantqiao
 *
 * @author tyrantqiao
 */
@Repository
@Mapper
public interface OrderMapper extends BaseMapper<Order> {
	/**
	 * 查询 貌似有更简单的方式
	 * 设立results {@code @Results({
	 *
	 * @param OrderId
	 * @return
	 * @Result(property = "orderId",column = "order_id",javaType = ),
	 * @Result(property = "orderUserId",column = "order_user_id")
	 * })}
	 */
	@Select(value = "select * from orders where orderUserId=#{orderUserId}")
	Order getByOrderUserId(@Param("orderUserId") Long orderUserId);

	/**
	 * 删除
	 *
	 * @param orderId
	 */
	@Delete(value = "delete from orders where orderId=#{orderId} ")
	void deleteByOrderId(@Param("orderId") Long orderId);

	/**
	 * 加入，貌似是需要标注全部字段的
	 *
	 * @param order
	 */
	@Insert(value = "INSERT INTO orders(order_id,order_user_id) values(#{orderId},#{orderUserId})")
	void insertOrder(Order order);

	/**
	 * 更新
	 *
	 * @param order
	 */
	@Update("UPDATE orders SET order_id={#orderId}")
	void updateOrder(Order order);


}
