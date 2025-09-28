package com.cuenta;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CuentaApplication {

	public static void main(String[] args) {
		SpringApplication.run(CuentaApplication.class, args);
	}

}
