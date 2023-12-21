package com.kosta.catdog.config.jwt;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kosta.catdog.repository.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.kosta.catdog.config.auth.PrincipalDetails;
import com.kosta.catdog.entity.User;
import com.kosta.catdog.repository.UserDslRepository;

// 인가 : 로그인 처리가 되어야 하는 요청이 들어왔을때 실행 
public class JwtAuthorizationFilter extends BasicAuthenticationFilter{
	
	private UserDslRepository userDslRepository;

//	private final UserRepository userRepository;

	public  JwtAuthorizationFilter(AuthenticationManager authenticationManager, UserDslRepository userDslRepository) {
		super(authenticationManager);
		this.userDslRepository = userDslRepository;
	}
	
	@Override
	protected void doFilterInternal(HttpServletRequest request,
									HttpServletResponse response,
									FilterChain chain) throws IOException, ServletException {
		System.out.println("JwtAuthorizationFilter doFilterInternal ===========");
		String header = request.getHeader(JwtProperties.HEADER_STRING);
//		String uri = request.getRequestURI(); // uri  만 가져오기
//		System.out.println("Uri : " + uri);

		System.out.println("header Authrization : " +header);
		
		if(header==null || !header.startsWith(JwtProperties.TOKEN_PREFIX)) {
			chain.doFilter(request, response);
			return;
		}
		
		String token=request.getHeader(JwtProperties.HEADER_STRING).replace(JwtProperties.TOKEN_PREFIX,"");
		
		// 토큰 검증
		String id = JWT.require(Algorithm.HMAC512(JwtProperties.SECRET)).build().verify(token).getClaim("id").asString();
		System.out.println(" Id : " + id );
		
		if(id!=null) {
			User user = userDslRepository.findById(id);
			
			PrincipalDetails principalDetails = new PrincipalDetails(user);
//			if(uri.contains("/catdog/usermy/shopreg")){
//				if(!(principalDetails.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_DES"))||
//						principalDetails.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_SHOP")))){
//				response.sendError(HttpServletResponse.SC_UNAUTHORIZED,"권한 없음");
//				return;
//				}
//			} else if(uri.contains("/catdog/usermy/shopregform")){
//				if(!(principalDetails.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_DES"))||
//						principalDetails.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_SHOP")))){
//					response.sendError(HttpServletResponse.SC_UNAUTHORIZED,"권한 없음");
//					return;
//				}
//			} else if (uri.contains("desmodi")){
//				if(!(principalDetails.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_DES"))||
//						principalDetails.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_SHOP")))){
//					response.sendError(HttpServletResponse.SC_UNAUTHORIZED,"권한 없음");
//					return;
//				}
//			}
			Authentication authentication = new UsernamePasswordAuthenticationToken(principalDetails, null, principalDetails.getAuthorities());
			
			SecurityContextHolder.getContext().setAuthentication(authentication);
		}
		
		chain.doFilter(request, response);
	}

}
