package com.tyrantQiao.stealth.mapper;

import com.tyrantQiao.stealth.POJO.Order;
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
	Order findByOrderId(@Param("orderId") Long OrderId);

	@Delete(value = "delete from order where orderId=#{orderId} ")
	Order deleteByOrderId(@Param("orderId") Long orderId);
}
