package com.liao;

import com.liao.util.FileProperties;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@MapperScan("com.liao.dao")
@EnableConfigurationProperties({
		FileProperties.class
})
public class DonutApplication {

	public static void main(String[] args) {
		SpringApplication.run(DonutApplication.class, args);
	}

}
