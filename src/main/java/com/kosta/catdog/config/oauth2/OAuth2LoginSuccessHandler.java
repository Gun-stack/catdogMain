package com.kosta.catdog.config.oauth2;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kosta.catdog.config.auth.PrincipalDetails;
import com.kosta.catdog.config.jwt.JwtProperties;
import com.kosta.catdog.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.Date;

@Component
public class OAuth2LoginSuccessHandler implements AuthenticationSuccessHandler {


	private ObjectMapper om = new ObjectMapper();

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {

		System.out.println("OAuth2LoginSuccessHandler 진입");
		PrincipalDetails principalDetails = (PrincipalDetails)authentication.getPrincipal();

		 User user = principalDetails.getUser();
		System.out.println("User : " + user);
		 if(user.getPassword()==null){
			 response.setCharacterEncoding("UTF-8");
			 user.setNickname(URLEncoder.encode(user.getNickname(),"UTF-8"));
			 String userInfo = om.writeValueAsString(user);

			 String targetUrl = UriComponentsBuilder.fromUriString("http://localhost:3000/catdog/oauthjoin/"+userInfo)
					 .build().toUriString();
			 System.out.println("targetUrl : " + targetUrl);
			 response.sendRedirect(targetUrl);
			 return;
		 }

		String jwtToken = JWT.create()
				.withSubject(principalDetails.getUsername())
				.withExpiresAt(new Date(System.currentTimeMillis()+JwtProperties.EXPIRATION_TIME))
				.withClaim("id", principalDetails.getUser().getId())
				.withClaim("nickname", principalDetails.getUser().getNickname())
				.withClaim("email",principalDetails.getUser().getEmail())
				.sign(Algorithm.HMAC512(JwtProperties.SECRET));
		System.out.println("Token : " + jwtToken);

		response.setCharacterEncoding("UTF-8");
		String targetUrl = UriComponentsBuilder.fromUriString("http://localhost:3000/catdog/oauth/redirect/"+JwtProperties.TOKEN_PREFIX+jwtToken)
				.build().toUriString();

		response.addHeader(JwtProperties.HEADER_STRING, JwtProperties.TOKEN_PREFIX+jwtToken);
		response.sendRedirect(targetUrl);

		System.out.println("targetUrl : " + targetUrl);
	}
}
