package com.liao;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@MapperScan("com.liao.dao")
//@EnableConfigurationProperties({
//		FileProperties.class
//})
//@EnableCaching // 开启缓存
public class DonutApplication {

	public static void main(String[] args) {
		SpringApplication.run(DonutApplication.class, args);
	}

}
