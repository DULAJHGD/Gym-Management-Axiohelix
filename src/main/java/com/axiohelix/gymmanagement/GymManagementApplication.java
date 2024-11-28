package com.axiohelix.gymmanagement;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
@MapperScan("com.axiohelix.gymmanagement.mapper")
public class GymManagementApplication{

	public static void main(String[] args) {
		SpringApplication.run(GymManagementApplication.class, args);
	}
}
