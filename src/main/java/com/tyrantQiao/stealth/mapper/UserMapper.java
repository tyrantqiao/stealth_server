package com.tyrantQiao.stealth.mapper;

import com.tyrantQiao.stealth.POJO.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * author: TyrantQiao
 * date: 2018/4/20
 * github: github.com/tyrantqiao
 */
@Mapper
public interface UserMapper {
	@Select("SELECT EXISTS (SELECT 1 FROM user WHERE id=#{id})")
	boolean isUserExist(@Param("id") Long id);

	@Select("SELECT * FROM user where id=#{id}")
	User findById(@Param("id") Long id);
}
