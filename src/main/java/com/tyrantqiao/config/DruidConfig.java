package com.tyrantqiao.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * Description:
 *
 * @author tyrantqiao [tyrantqiao@gmail.com]
 * @see <a href="https://github.com/tyrantqiao">qiao's github</a>
 */
@Configuration
public class DruidConfig {
	@Bean
	@ConfigurationProperties(prefix = "spring.datasource")
	public DataSource druisDataSource() {
		return new DruidDataSource();
	}
}
