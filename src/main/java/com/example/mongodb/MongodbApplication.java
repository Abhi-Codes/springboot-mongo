package com.example.mongodb;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Optional;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.auditing.DateTimeProvider;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories(basePackages = "com.example.mongodb.repository")
@EnableMongoAuditing(dateTimeProviderRef = "utcDateTimeProvider")
public class MongodbApplication {

	public static void main(String[] args) {
		SpringApplication.run(MongodbApplication.class, args);
	}
	
	 @Bean
	 public DateTimeProvider utcDateTimeProvider() {
	        return () -> Optional.of(LocalDateTime.now(ZoneOffset.UTC));
	    }

}
