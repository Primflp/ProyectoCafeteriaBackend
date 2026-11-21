package com.cafeteria.ms_boleta;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class MsBoletaApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsBoletaApplication.class, args);
	}

}
