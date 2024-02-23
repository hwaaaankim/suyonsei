package com.dev.SuYonsei.config;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.thymeleaf.extras.springsecurity6.dialect.SpringSecurityDialect;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig {


	@Bean
	SpringSecurityDialect springSecurityDialect() {
		return new SpringSecurityDialect();
	}

	@Bean
	WebSecurityCustomizer webSecurityCustomizer() {
		return (web) -> web.ignoring().requestMatchers(PathRequest.toStaticResources().atCommonLocations());
	}

	
	
	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		
		http.csrf((csrfConfig) -> 
				csrfConfig
					.disable()) // 1번
			.headers((headerConfig) -> 
				headerConfig
					.frameOptions(frameOptionsConfig -> frameOptionsConfig.disable()))
			.authorizeHttpRequests((authorizeRequests) -> 
				authorizeRequests
					.requestMatchers(new AntPathRequestMatcher("/admin/**")).hasRole("ADMIN")
					.requestMatchers(new AntPathRequestMatcher("/**")).permitAll()
					.anyRequest().authenticated())
			.formLogin((formLogin) -> 
				formLogin
					.loginPage("/loginForm")
					.usernameParameter("username")
					.passwordParameter("password")
					.loginProcessingUrl("/signinProcess")
					.defaultSuccessUrl("/admin/clientManager", true))
			.logout((logoutConfig) -> 
				logoutConfig
					.logoutUrl("/admin/logout")
					.deleteCookies("JSESSIONID")
					.invalidateHttpSession(true)
					.logoutSuccessUrl("/"));
		return http.build();
	}

}
