package com.student.management;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {

			BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
			String encoded = encoder.encode("1234");
			System.out.println(encoded);
		SpringApplication.run(Application.class, args);
	}

}
