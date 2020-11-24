package com.nasim.config;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.nasim.jwt.JwtTokenFilter;
import com.nasim.jwt.MyAuthenticationEntryPoint;
import com.nasim.security.CustomUserDetailsService;



@Configuration
@EnableWebSecurity
@Import(SwaggerApiConfig.class)
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private  CustomUserDetailsService customUserDetailsService;
	@Autowired
    private  MyAuthenticationEntryPoint myAuthenticationEntryPoint;
	@Bean
	public JwtTokenFilter authenticationFilter() {
		return new JwtTokenFilter();
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
    public ModelMapper getModelMapper() {
        return new ModelMapper();
    }
	
	
	@Override
	public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
		authenticationManagerBuilder.userDetailsService(customUserDetailsService).passwordEncoder(passwordEncoder());
	}

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	
	

	 @Override
	    protected void configure(HttpSecurity http) throws Exception {
		 http.cors()
         .and()
         .csrf()
         .disable()
         .exceptionHandling()
         .authenticationEntryPoint(myAuthenticationEntryPoint)
         .and()
         .sessionManagement()
         .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
         .and()
         .authorizeRequests()
         .antMatchers("/api/v1/**","/api/v1/login").permitAll()
         .antMatchers("/api/v1/roles","/api/v1/signup").permitAll()
         .anyRequest()
         .authenticated();
		 
		 http.addFilterBefore(authenticationFilter(), UsernamePasswordAuthenticationFilter.class);
		 
	       
	    }

	 
}
