package com.tyrantQiao.stealth.security;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.stereotype.Component;

@Component
@EnableWebSecurity
public class StealthSecurity extends WebSecurityConfigurerAdapter {
	/**
	 * @param http object handler
	 * @throws Exception exception handler
	 *                   <p>
	 *                   .antMatchers() make a List<RequestMatcher>
	 *                   .permitAll() add this requestMatcher to ExpressionInterceptUrlRegistry REGISTRY
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		//When we don't use browser to send a form, that would be a csrf request, but I think there might be some way to get through, like TODO endpoints?
		http.authorizeRequests()
				.antMatchers("/register").permitAll()
				.antMatchers("/confirm").permitAll()
				.antMatchers("/login").permitAll()
				.and()
				.csrf().disable() ;
//				.httpBasic();
//				.httpBasic().authenticationEntryPoint(entryPoint);

	}
}
