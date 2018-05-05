package com.tyrantqiao.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * Description:
 * 当使用spring-boot-druid-starter时可以通过配置文件选择，或者继承类，调用方法来实现
 * 像servlet这些就是spring-boot-druid-starter自动配置好的
 *
 * 但是我们仍然需要自己调出druidDataSource，不然spring是无法启动druid的，因为它需要调用一次。
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
