package com.kosta.catdog.config.jwt;

public interface JwtProperties {
	String SECRET = "CatDog";  //우리 서버 고유의 비밀키
	int EXPIRATION_TIME = 60000*60*24; //24시간
	String TOKEN_PREFIX = "Bearer ";
	String HEADER_STRING = "Authorization";

}
