package com.example.shareSphere;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.example.shareSphere.mapper")
public class ProductorDemo1 {

	public static void main(String[] args) {
		SpringApplication.run(ProductorDemo1.class, args);
	}

}
