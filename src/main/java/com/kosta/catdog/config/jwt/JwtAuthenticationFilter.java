package com.kosta.catdog.config.jwt;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kosta.catdog.config.auth.PrincipalDetails;
import com.kosta.catdog.dto.LoginRequestDto;
import com.kosta.catdog.entity.User;

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
			response.setCharacterEncoding("UTF-8");
			response.setContentType("application/json;charset=UTF-8");
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

		try{
			String userJson = om.writeValueAsString(user);
			response.getWriter().write(userJson);
//			System.out.println("User Object To JSON !!!!!!");
//			System.out.println(userJson);
		} catch( Exception e) {
			e.printStackTrace();
		}

		response.addHeader(JwtProperties.HEADER_STRING, JwtProperties.TOKEN_PREFIX+jwtToken);
	}
	
	@Override
	protected void unsuccessfulAuthentication(HttpServletRequest request,
	                                          HttpServletResponse response,
	                                          AuthenticationException failed)
	        throws IOException, ServletException {
	    response.setCharacterEncoding("UTF-8");
	    response.setContentType("application/json;charset=UTF-8");

	    // 예외의 유형을 확인하여 인증 실패의 원인을 결정합니다.
	    if (failed instanceof BadCredentialsException) {
	        // 제공된 자격 증명이 잘못되었습니다.
	        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
	        response.getWriter().write("아이디가 없습니다.");
	    } else if (failed instanceof LockedException) {
	        // 필요한 경우 계정 잠금을 처리합니다.
	        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
	        response.getWriter().write("{'message':'계정이 잠겨 있습니다'}");
	    } else {
	        // 기타 인증 실패에 대한 일반적인 오류 메시지
	        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
	        response.getWriter().write("{'message':'인증 실패'}");
	    }
	}
	
	
}
