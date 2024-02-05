package com.superhero;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.superhero.Repository")
@EnableWebSecurity
public class HeroApplication {

	public static void main(String[] args) {
		SpringApplication.run(HeroApplication.class, args);
	}

	@Bean
	public SecurityFilterChain createSecurityFilterChain(HttpSecurity http) throws Exception{
		http.authorizeHttpRequests(authz -> authz
				.anyRequest().permitAll());
		return http.build();
	}

}
