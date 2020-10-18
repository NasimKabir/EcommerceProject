package com.nasim.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.nasim.jwt.JwtTokenFilter;
import com.nasim.security.CustomUserDetailsService;



@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
@Import(SwaggerApiConfig.class)
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
    private final CustomUserDetailsService customUserDetailsService;
	private final PasswordEncoder passwordEncoder;
	private final MyAuthenticationEntryPoint authenticationEntryPoint;
	private final JwtTokenFilter jwtTokenFilter;
    
	@Autowired
	public SecurityConfig(CustomUserDetailsService customUserDetailsService, PasswordEncoder passwordEncoder,
			MyAuthenticationEntryPoint authenticationEntryPoint) {
		super();
		this.customUserDetailsService = customUserDetailsService;
		this.passwordEncoder = passwordEncoder;
		this.authenticationEntryPoint = authenticationEntryPoint;
		this.jwtTokenFilter = new JwtTokenFilter();
	}
	
	

    
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(customUserDetailsService).passwordEncoder(passwordEncoder);
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
           .csrf().disable()
           .exceptionHandling()
           .authenticationEntryPoint(authenticationEntryPoint)
           .and()
           .sessionManagement()
           .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
           .and()
           .authorizeRequests()
           .antMatchers("/login").permitAll()
           .antMatchers("/api/v1/**").permitAll()
           .antMatchers("/v2/api-docs").permitAll()
           .antMatchers("/swagger-ui.html").permitAll()
           .anyRequest()
           .authenticated();
        http.addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class);
    }
    
	

}
