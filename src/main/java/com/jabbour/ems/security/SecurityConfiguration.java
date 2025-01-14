package com.jabbour.ems.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

/***
 *  Class for Spring Security Configuration and turning on Spring Security for the application.
 *  more about Vaadin security features on {@linkplain https://vaadin.com/security }
 * 
 * NOTE: it is not good practice to configure users 
 * directly in code for applications in production. 
 * 
 * You can configure Spring Security to use an authentication provider
 * further reading: https://dzone.com/articles/spring-security-authentication
 * @author chris
 * 
 * */
@EnableWebSecurity
@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	private final AppUserDetailsService userDetailsService;
	private final PasswordEncoder encoder;

	private static final String LOGIN_PROCESSING_URL = "/login";
	private static final String LOGIN_FAILURE_URL = "/login?error";
	private static final String LOGIN_URL = "/login";
	private static final String LOGIN_SUCCESS_URL = "/login";

	public SecurityConfiguration(AppUserDetailsService userDetailsService, PasswordEncoder encoder) {
		this.userDetailsService = userDetailsService;
		this.encoder = encoder;
	}

	/***
	 * Block unauthenticated requests to all pages besides login page
	 */
	protected void configure(HttpSecurity http) throws Exception {
		//example of method chaining
		http.csrf().disable() // Disable CSRF protection from Spring Security as Vaadin already has it built in.
		.requestCache().requestCache(new CustomRequestCache())
		.and().authorizeRequests()
		.requestMatchers(SecurityUtils::isFrameworkInternalRequest).permitAll()
		
		.anyRequest().authenticated()
		
		.and().formLogin() //Enable form-based login and permits even unauth to it
		.loginPage(LOGIN_URL).permitAll()
		.loginProcessingUrl(LOGIN_PROCESSING_URL)
		.failureUrl(LOGIN_FAILURE_URL)
		.and()
//				.logout().logoutSuccessUrl(LOGIN_SUCCESS_URL)
				.logout() // This is missing and is important
				.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
				.logoutSuccessUrl(LOGIN_SUCCESS_URL);;
	}
	
	@Bean
	@Override
	/**
	 * Configure test users for application. for testing and demonstration purposes.
	 */
	public UserDetailsService userDetailsService() {
		return userDetailsService;
	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(encoder);
	}

	@Override
	public void configure(WebSecurity web) {
		web.ignoring().antMatchers(
				//client side JS
				"/VAADIN/**", 
				"/favicon.ico",
				"/robots.txt",
				"/manifest.webmanifest", 
				"/sw.js", 
				"/offline.html", 
				"/icons/**",
				"/images/**",
				"/styles/**",
				"/h2-console/**");
	}
	
	
}

