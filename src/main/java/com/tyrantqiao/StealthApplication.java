package com.tyrantqiao;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author tyrantqiao [tyrantqiao@gmail.com]
 */
@SpringBootApplication
@MapperScan("com.tyrantqiao.mapper")
public class StealthApplication {
	public static void main(String[] args) {
		SpringApplication.run(StealthApplication.class, args);
	}
}
                                            