package br.com.itauconsignado.contratoservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class ContratoServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ContratoServiceApplication.class, args);
	}

}
