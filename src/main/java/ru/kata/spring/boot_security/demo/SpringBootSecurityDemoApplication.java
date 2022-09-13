package ru.kata.spring.boot_security.demo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class SpringBootSecurityDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootSecurityDemoApplication.class, args);
	}
	@Bean
	CommandLineRunner run(RoleService roleService, UserService userService) {
		return args -> {
			roleService.save(new Role("ROLE_USER"));
			roleService.save(new Role("ROLE_ADMIN"));
			byte age1 = 38;
			byte age2 = 48;
			System.out.println(roleService.findAll());

			User user1 = new User("Jon", "Snow",  age1, "jonsnow", "xxx");
			User user2 = new User("John", "Wick",  age2, "wick", "xxx");

			Set<Role> roles1 = new HashSet<>();
			roles1.add(roleService.findByName("ROLE_USER"));
			roles1.add(roleService.findByName("ROLE_ADMIN"));
			Set<Role> roles2 = new HashSet<>();
			roles2.add(roleService.findByName("ROLE_USER"));

			user1.setRoles(roles1);
			user2.setRoles(roles2);

			userService.saveUser(user1);
			userService.saveUser(user2);

			System.out.println(userService.findAll());
		};
	}
}
