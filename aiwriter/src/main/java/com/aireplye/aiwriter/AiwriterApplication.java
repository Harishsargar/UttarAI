package com.aireplye.aiwriter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class AiwriterApplication {

	public static void main(String[] args) {
		SpringApplication.run(AiwriterApplication.class, args);
	}

}
