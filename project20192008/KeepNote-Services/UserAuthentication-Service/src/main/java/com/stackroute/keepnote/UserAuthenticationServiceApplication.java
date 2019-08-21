package com.stackroute.keepnote;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

import com.stackroute.keepnote.config.CrossFilter;

@SpringBootApplication
public class UserAuthenticationServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserAuthenticationServiceApplication.class, args);
	}

	@Bean
	public FilterRegistrationBean<CrossFilter> CrossFilter() {
		FilterRegistrationBean<CrossFilter> registrationBean = new FilterRegistrationBean<>();
		registrationBean.setFilter(new CrossFilter());

		return registrationBean;
	}
}
