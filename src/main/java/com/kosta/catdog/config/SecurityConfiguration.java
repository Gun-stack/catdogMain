package com.kosta.catdog.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.filter.CorsFilter;

import com.kosta.catdog.config.jwt.JwtAuthenticationFilter;
import com.kosta.catdog.config.jwt.JwtAuthorizationFilter;
import com.kosta.catdog.config.oauth2.OAuth2LoginSuccessHandler;
import com.kosta.catdog.config.oauth2.PrincipalOauth2UserService;
import com.kosta.catdog.repository.UserDslRepository;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter{
	@Autowired
	private CorsFilter corsFilter;

	@Autowired
	private CorsConfig corsConfig;

	@Autowired
//	private UserRepository userRepository;
	private UserDslRepository userDslRepository;

	@Autowired
	private PrincipalOauth2UserService principalOauth2UserService;

	@Autowired
	private OAuth2LoginSuccessHandler oAuth2LoginSuccessHandler;

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	public void configure(HttpSecurity http) throws Exception {

		System.out.println("SecurityConfiguration 기본 로그인 =================");
		http
			.addFilter(corsConfig.corsFilter())  // 다른 도메인 접근 허용
			.csrf().disable()  //csrf 공격 비활성화 
			.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);// session 비활성화
		// Login
		http
			.formLogin().disable()  // 로그인 폼 사용 비활성화 
			.httpBasic().disable()  // httpBasic은 header에 username, password를 암호화하지 않은 상태로 주고 받는다. 이를 사용하지 않겠다.
			.addFilter(new JwtAuthenticationFilter(authenticationManager())); // UsernamePasswordAuthenticationFilter


		System.out.println("SecurityConfiguration 소셜 로그인 =================");

		//oauth2Login
		http
				.oauth2Login()
				.authorizationEndpoint().baseUri("/oauth2/authorization")  // 소셜 로그인 url
				.and()
				.redirectionEndpoint().baseUri("/oauth2/callback/*")  // 소셜 인증 후 redirect url
				.and()
				.userInfoEndpoint().userService(principalOauth2UserService)  // 회원 정보 처리
				.and()
				.successHandler(oAuth2LoginSuccessHandler);


		System.out.println("SecurityConfiguration Role =================");

		// 로그인 이후 권한처리
		http
				.addFilter(new JwtAuthorizationFilter(authenticationManager(), userDslRepository)) //BasicAuthenticationFilter
				.authorizeRequests()
				.antMatchers("/catdog/usermy/shopreg").access("hasRole('ROLE_DES') or hasRole('ROLE_SHOP')")
				.antMatchers("/catdog/usermy/shopregform").access("hasRole('ROLE_DES') or hasRole('ROLE_SHOP')")
				.antMatchers("/catdog/usermy/desmodi").access("hasRole('ROLE_DES') or hasRole('ROLE_SHOP')")
				.anyRequest().permitAll(); // 나머지는 허용
	}

	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
		return authenticationConfiguration.getAuthenticationManager();

	}



}