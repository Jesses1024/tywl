package com.puxintech.tywl;

import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.puxintech.tywl.resolver.CurrentUserMethodArgumentResovler;
import com.puxintech.tywl.resolver.PageRequestMethodArgumentResolver;
import com.puxintech.tywl.security.JsonUsernamePasswordAuthenticationFilter;

@SpringBootApplication
public class TywlServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(TywlServerApplication.class, args);
	}

	@Configuration
	public static class WebConfig extends WebMvcConfigurerAdapter {
		@Override
		public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
			argumentResolvers.add(new PageRequestMethodArgumentResolver());
			argumentResolvers.add(new CurrentUserMethodArgumentResovler());
		}
	}

	@EnableWebSecurity
	public static class SecurityConfig extends WebSecurityConfigurerAdapter {
		@Override
		protected void configure(HttpSecurity http) throws Exception {
			http.csrf().disable();
			http.addFilterBefore(new JsonUsernamePasswordAuthenticationFilter(this.authenticationManagerBean()),
					BasicAuthenticationFilter.class);

			http.authorizeRequests().anyRequest().authenticated();
		}
	}

}
