package com.tyrantQiao.stealth.mapper;

import com.tyrantQiao.stealth.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

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

	@Select("SELECT * FROM user where enabled=true")
	List<User> getAll();
}
