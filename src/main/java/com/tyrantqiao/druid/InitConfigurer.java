package com.tyrantqiao.druid;

import com.tyrantqiao.entity.User;
import com.tyrantqiao.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import javax.annotation.PostConstruct;

/**
 * Description: 初始化数据源
 *
 * @author tyrantqiao [tyrantqiao@gmail.com]
 * @see <a href="https://github.com/tyrantqiao">qiao's github</a>
 */
@Configuration
@Profile("default")
public class InitConfigurer {
	@Autowired
	private UserMapper userMapper;

	@PostConstruct
	public void init() {
		User user = userMapper.getById(1L);
		System.out.println(user);
	}
}
