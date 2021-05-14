package com.designprojectteam.easytravelling;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.config.EnableMongoAuditing;

@SpringBootApplication
@EnableMongoAuditing
public class EasytravellingApplication {

	public static void main(String[] args) {
		SpringApplication.run(EasytravellingApplication.class, args);
	}

}
