package fr.insa.soa.ExchangeSemester.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfigView implements WebMvcConfigurer {

	public void addViewControllers(ViewControllerRegistry registry) {
		
		//differents views mapped to URL (index ==> index.html in src/main/templates...)
		
		registry.addViewController("/").setViewName("index");
		registry.addViewController("/register").setViewName("register");
		registry.addViewController("/login").setViewName("login");
		registry.addViewController("/home").setViewName("home");
		registry.addViewController("/student/home").setViewName("homeStudent");
		registry.addViewController("student/apply").setViewName("homeStudent_apply");
		registry.addViewController("/university/home").setViewName("homeUniversity");
		registry.addViewController("/insa/home").setViewName("homeINSA");

	}
}