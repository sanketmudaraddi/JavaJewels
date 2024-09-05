package com.houseof.johari;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;


@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})

public class HouseOfJohariApplication {

	public static void main(String[] args) {
		SpringApplication.run(HouseOfJohariApplication.class, args);
	}

}
