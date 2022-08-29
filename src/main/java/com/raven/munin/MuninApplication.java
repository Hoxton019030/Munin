package com.raven.munin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties
public class MuninApplication {

	public static void main(String[] args) {
		SpringApplication.run(MuninApplication.class, args);
	}

}
