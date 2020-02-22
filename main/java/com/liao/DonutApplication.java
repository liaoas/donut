package com.liao;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.liao.dao")
public class DonutApplication {

	public static void main(String[] args) {
		SpringApplication.run(DonutApplication.class, args);
	}

}
