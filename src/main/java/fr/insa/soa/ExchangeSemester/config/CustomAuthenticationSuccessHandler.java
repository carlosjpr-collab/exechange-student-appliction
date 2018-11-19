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

		switch (authentication.getAuthorities().toString()) {
		case "[ROLE_ADMIN]":
			response.sendRedirect("/student/home");
			break;
		case "[ROLE_STUDENT]":
			response.sendRedirect("/student/home");
			break;
		case "[ROLE_UNIVERSITY]":
			response.sendRedirect("/university/home");
			break;
		case "[ROLE_INSA]":
			response.sendRedirect("/insa/home");
			break;
		default:
			break;
		}

	}

}
