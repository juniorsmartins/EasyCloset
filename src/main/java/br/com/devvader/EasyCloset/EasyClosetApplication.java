package br.com.devvader.EasyCloset;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class EasyClosetApplication {

	public static void main(String[] args) {
		SpringApplication.run(EasyClosetApplication.class, args);
	}
}
