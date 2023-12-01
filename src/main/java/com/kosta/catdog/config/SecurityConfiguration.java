package com.kosta.catdog.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.kosta.catdog.config.jwt.JwtAuthenticationFilter;
import com.kosta.catdog.config.jwt.JwtAuthorizationFilter;
import com.kosta.catdog.repository.UserDslRepository;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter{
	
	@Autowired
	public CorsConfig corsConfig;
	
	@Autowired
	private UserDslRepository userDslRepository;


	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Override
	public void configure(HttpSecurity http) throws Exception {
		http
			.addFilter(corsConfig.corsFilter())  // 다른 도메인 접근 허용  
			.csrf().disable()  //csrf 공격 비활성화 
			.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)// session 비활성화 
			.and()
			.formLogin().disable()  // 로그인 폼 사용 비활성화 
			.httpBasic().disable()  // httpBasic은 header에 username, password를 암호화하지 않은 상태로 주고 받는다. 이를 사용하지 않겠다. 
			.addFilter(new JwtAuthenticationFilter(authenticationManager()))
//			.addFilter(new JwtAuthorizationFilter(authenticationManager(), userDslRepository))
			
			// 로그인 이후 권한 체크 start  
			.authorizeRequests()
			.antMatchers("/user/**").access("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN') or hasRole('ROLE_MANAGER')")//로그인
			.antMatchers("/manager/**").access("hasRole('ROLE_MANAGER') or hasRole('ROLE_ADMIN')") // 
			.antMatchers("/admin/**").access("hasRole('ROLE_ADMIN')") // 로그인 & 권한 
			.anyRequest().permitAll(); // 나머지는 허용한다 
	}
	
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
		return authenticationConfiguration.getAuthenticationManager();
		
	}
	
	

}