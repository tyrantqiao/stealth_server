package com.tyrantQiao.stealth.mapper;

import com.tyrantQiao.stealth.entity.Orders;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * author: TyrantQiao
 * date: 2018/4/19
 * github: github.com/tyrantqiao
 */
@Mapper
public interface OrderMapper {
	@Select(value = "select * from order where orderId=#{orderId}")
	Orders findByOrderId(@Param("orderId") Long OrderId);

	@Delete(value = "delete from order where orderId=#{orderId} ")
	Orders deleteByOrderId(@Param("orderId") Long orderId);
}
