package com.EBA;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.EBA.Model")
public class EbaProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(EbaProjectApplication.class, args);
	}

}
