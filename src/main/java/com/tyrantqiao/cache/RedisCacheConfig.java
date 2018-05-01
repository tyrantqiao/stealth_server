package com.tyrantqiao.cache;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Configuration;

/**
 * Description: redis cache config的配置，但其实这个并不重要
 * 重要的是在properties里面设置了使用cache.type=redis
 *
 * @author tyrantqiao [tyrantqiao@gmail.com]
 * @see <a href="https://github.com/tyrantqiao">qiao's github</a>
 */
@Configuration
@EnableCaching
public class RedisCacheConfig {
	private Logger logger = LoggerFactory.getLogger(this.getClass());

}
