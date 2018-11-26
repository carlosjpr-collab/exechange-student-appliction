package fr.insa.soa.ExchangeSemester.config;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		//if the user successed to authenticate himself : 

		switch (authentication.getAuthorities().toString()) {
		case "[ROLE_ADMIN]":	//an admin will be redirected to the student home 
			response.sendRedirect("/student/home");
			break;
		case "[ROLE_STUDENT]":   //a student will be redirected to the student home 
			response.sendRedirect("/student/home");
			break;
		case "[ROLE_UNIVERSITY]":	//a user of university will be redirected to the university home 
			response.sendRedirect("/university/home");
			break;
		case "[ROLE_INSA]":	 //a user of insa will be redirected to the insa home 
			response.sendRedirect("/insa/home");
			break;
		default:
			break;
		}

	}

}
