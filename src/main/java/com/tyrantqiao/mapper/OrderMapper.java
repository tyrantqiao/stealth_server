package com.tyrantqiao.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.tyrantqiao.entity.Order;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

/**
 * date: 2018/4/19
 * github: github.com/tyrantqiao
 * 注意这些字段名字不能错，下划线尤其要注意
 *
 * @author tyrantqiao [tyrantqiao@gmail.com]
 */
@Repository
@Mapper
public interface OrderMapper extends BaseMapper<Order> {
	/**
	 * 查询 貌似有更简单的方式
	 * 设立results {@code @Results({
	 *
	 * @param orderUserId userId
	 * @return order
	 * @Result(property = "orderId",column = "order_id",javaType = ),
	 * @Result(property = "orderUserId",column = "order_user_id")
	 * })}
	 */
	@Select(value = "select * from orders where order_user_id=#{orderUserId}")
	Order getByOrderUserId(@Param("orderUserId") Long orderUserId);

	/**
	 * 删除
	 *
	 * @param orderId
	 */
	@Delete(value = "delete from orders where order_id=#{orderId} ")
	void deleteByOrderId(@Param("orderId") Long orderId);

	/**
	 * 加入，是需要标注全部字段的
	 * 首先实体类必须序列化{@code implements Serializable}
	 * 然后是插入类的字段，使用{@code #{xxx}}形式即可
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
