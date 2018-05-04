package com.tyrantqiao;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 如果遇到了8080端口被占用的情况（指windows）
 * 使用{@code netstat -ano |findstr :8080},再用{@code taskkill /PID pid_number /F}杀死进程
 *
 * @author tyrantqiao [tyrantqiao@gmail.com]
 */
@SpringBootApplication
@MapperScan("com.tyrantqiao.mapper")
public class StealthApplication {
	public static void main(String[] args) {
		SpringApplication.run(StealthApplication.class, args);
	}
}
                                            