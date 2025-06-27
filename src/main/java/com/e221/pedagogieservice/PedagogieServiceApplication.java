package com.e221.pedagogieservice;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.Arrays;

@SpringBootApplication
public class PedagogieServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(PedagogieServiceApplication.class, args);
	}
}
