package com.greenhair.template;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.header.writers.StaticHeadersWriter;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
	public void configure(WebSecurity web) throws Exception
	{
        web.ignoring().antMatchers("/css/**", "/script/**", "image/**", "/fonts/**", "lib/**");
	}
	@Override
	protected void configure(HttpSecurity http) throws Exception
	{
        http.csrf().disable()
            .authorizeRequests()
			.antMatchers("/admin/**").hasRole("ADMIN")
            .antMatchers("/**").permitAll()
            .and()
            .headers()
            .addHeaderWriter(new StaticHeadersWriter("X-Content-Security-Policy","script-src 'self'"))
            .frameOptions().disable();
	}
}