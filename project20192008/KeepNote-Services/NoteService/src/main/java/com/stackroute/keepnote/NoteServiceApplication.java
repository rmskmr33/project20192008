package com.stackroute.keepnote;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

import com.stackroute.keepnote.config.CrossFilter;

/*
 * The @SpringBootApplication annotation is equivalent to using @Configuration, @EnableAutoConfiguration 
 * and @ComponentScan with their default attributes
 */

@SpringBootApplication
public class NoteServiceApplication {

	/*
	 * 
	 * You need to run SpringApplication.run, because this method start whole spring
	 * framework. Code below integrates your main() with SpringBoot
	 */

	public static void main(String[] args) {
		SpringApplication.run(NoteServiceApplication.class, args);
	}
	
	@Bean
	public FilterRegistrationBean<CrossFilter> CrossFilter() {
		FilterRegistrationBean<CrossFilter> registrationBean = new FilterRegistrationBean<>();
		registrationBean.setFilter(new CrossFilter());

		return registrationBean;
	}
}
