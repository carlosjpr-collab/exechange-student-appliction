package fr.insa.soa.ExchangeSemester.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.MessageDigestPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SuppressWarnings("deprecation")
@Configuration
@EnableAutoConfiguration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	DataSource dataSource;
	
	@Autowired
	CustomAuthenticationSuccessHandler authenticationSuccessHandler;

	@Autowired
	public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {
		auth.jdbcAuthentication().dataSource(dataSource)
				.usersByUsernameQuery("select login,password, enabled from user where login=?")
				.authoritiesByUsernameQuery(
						"select u.login, r.description from user u inner join roles r on(u.id_role=r.id_role) where u.login=?")
				.passwordEncoder(passwordEncoder());
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new MessageDigestPasswordEncoder("MD5");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.authorizeRequests()
				.antMatchers("/","/register","/service/**").permitAll()
				.antMatchers("/js/**").permitAll()
				.antMatchers("/css/**").permitAll()
				.antMatchers("/images/**").permitAll()
				.antMatchers("/student/**").hasAnyAuthority("ROLE_STUDENT,ROLE_ADMIN")
				.antMatchers("/university/**").hasAnyAuthority("ROLE_UNIVERSITY,ROLE_ADMIN")
				.antMatchers("/insa/**").hasAnyAuthority("ROLE_INSA,ROLE_ADMIN")
				.anyRequest().authenticated()
				.and()
			.formLogin()
				.loginPage("/login").successHandler(authenticationSuccessHandler)
				.permitAll()
				.and()
			.logout()	
				.permitAll()
				.and()
			.csrf().disable();;
	}
}