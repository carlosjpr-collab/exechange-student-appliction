package fr.insa.soa.ExchangeSemester.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RootController {

	@GetMapping("/")
	public String redirectLogin() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();

		if (auth.isAuthenticated()) {
			switch (auth.getAuthorities().toString()) {
			case "[ROLE_ADMIN]":
				return "redirect:/student/home";
			case "[ROLE_STUDENT]":
				return "redirect:/student/home";
			case "[ROLE_UNIVERSITY]":
				return "redirect:/univ/home";
			case "[ROLE_INSA]":
				return "redirect:/insa/home";
			default:
				return "index";
			}

		} else {
			return "index";
		}
	}
}