package com.giggal.initask;

import java.util.logging.Logger;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import lombok.RequiredArgsConstructor;

@SpringBootApplication
@RequiredArgsConstructor
public class InitaskApplication {

	private final Logger logger = Logger.getLogger(String.valueOf(getClass()));

	public static void main(String[] args) {
		SpringApplication.run(InitaskApplication.class, args);
	}

}
