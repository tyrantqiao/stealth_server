package com.tyrantQiao.stealth;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.tyrantQiao.stealth.mapper")
public class StealthApplication {
	public static void main(String[] args) {
		SpringApplication.run(StealthApplication.class, args);
	}
}
                                            