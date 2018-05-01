package com.tyrantqiao.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.tyrantqiao.entity.Orders;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * date: 2018/4/19
 * github: github.com/tyrantqiao
 *
 * @author tyrantqiao
 */
@Repository
@Mapper
public interface OrderMapper extends BaseMapper<Orders> {
	@Select(value = "select * from order where orderId=#{orderId}")
	Orders findByOrderId(@Param("orderId") Long OrderId);

	@Delete(value = "delete from order where orderId=#{orderId} ")
	Orders deleteByOrderId(@Param("orderId") Long orderId);
}
