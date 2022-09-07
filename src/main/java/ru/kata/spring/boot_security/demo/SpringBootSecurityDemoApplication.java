package ru.kata.spring.boot_security.demo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.util.ArrayList;
import java.util.HashSet;

@SpringBootApplication
public class SpringBootSecurityDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootSecurityDemoApplication.class, args);
	}
	@Bean
	CommandLineRunner run(UserService userService) {
		return args -> {
			userService.saveRole(new Role(1L,"ROLE_USER"));
			userService.saveRole(new Role(2L, "ROLE_ADMIN"));

			userService.saveUser(new User(1L, "Jon", "Snow", (byte) 34, "jonsnow", "xxx", new HashSet<>()));
			userService.saveUser(new User(2L, "John", "Wick", (byte) 48, "wick", "xxx", new HashSet<>()));

			userService.addRoleToUser("jonsnow", "ROLE_USER");

			userService.addRoleToUser("wick", "ROLE_USER");
			userService.addRoleToUser("wick", "ROLE_ADMIN");
		};
	}
}
