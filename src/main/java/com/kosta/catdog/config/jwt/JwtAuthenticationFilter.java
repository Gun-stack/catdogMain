package com.kosta.catdog.config.jwt;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kosta.catdog.entity.User;
import com.kosta.catdog.repository.UserDslRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kosta.catdog.config.auth.PrincipalDetails;
import com.kosta.catdog.dto.LoginRequestDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter{
	

	private final AuthenticationManager authenticationManager;
	private ObjectMapper om = new ObjectMapper();


	
	// 인증 요청싱 실행되는 함수 =>/login
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
		
		System.out.println("Authentication : 진입 ");
		//request에 있는 username과 password를 파싱해서 자바 Object로 받기 

		LoginRequestDto loginRequestDto = null;
		try {
		 
			loginRequestDto = om.readValue(request.getInputStream(), LoginRequestDto.class);
		} catch(Exception e) {
			e.printStackTrace();
		}
		System.out.println("AuthenticationFilter : " + loginRequestDto);
		UsernamePasswordAuthenticationToken authenticationToken = 
				new UsernamePasswordAuthenticationToken(loginRequestDto.getId(), loginRequestDto.getPassword());
		System.out.println("JwtAuthenticationFilter : 토큰 생성 완료 ");
		// authenticate 함수가 호출되면 인증 프로바이더가 유저 디테일 서비스의
		// loadUserByUsername(토큰의 첫번째 파라미터) 를 호출하고
		// UserDetails를 리턴받아 토큰의 두번째 파라미터(credential)과
		// UserDetauls(DB값)의 getPassword() 함수로 비교해서 동일하면
		// Authentication 객체를 만들어서 필터체인으로 리턴해준다.  
		
		Authentication authentication = authenticationManager.authenticate(authenticationToken);
		PrincipalDetails principalDetails = (PrincipalDetails)authentication.getPrincipal();
		System.out.println("UserName0 :" + principalDetails.getUser().getName());
		
		return authentication;
	}
	
	@Override
	protected void successfulAuthentication(HttpServletRequest request, 
			                                HttpServletResponse response, 
			                                FilterChain chain, 
			                                Authentication authentication) throws IOException, ServletException {
		PrincipalDetails principalDetails = (PrincipalDetails)authentication.getPrincipal();
		String jwtToken = JWT.create()
							 .withSubject(principalDetails.getUsername())
							 .withExpiresAt(new Date(System.currentTimeMillis()+JwtProperties.EXPIRATION_TIME))
							 .withClaim("id", principalDetails.getUser().getId())
							 .withClaim("nickname", principalDetails.getUser().getNickname())
							 .sign(Algorithm.HMAC512(JwtProperties.SECRET));

		User user = principalDetails.getUser();
		System.out.println(user.getName());
		System.out.println(user.getNickname());

		try{
			response.setCharacterEncoding("UTF-8");
			response.setContentType("application/json;charset=UTF-8");
			String userJson = om.writeValueAsString(user);
			response.getWriter().write(userJson);
		} catch( Exception e) {
			e.printStackTrace();
		}

		response.addHeader(JwtProperties.HEADER_STRING, JwtProperties.TOKEN_PREFIX+jwtToken);
	}
	
	
	
}
