package br.com.itauconsignado.simulacaoservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.jdbc.repository.config.EnableJdbcAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableFeignClients
public class SimulacaoServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SimulacaoServiceApplication.class, args);
	}

}
