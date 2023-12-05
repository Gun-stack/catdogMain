package com.kosta.catdog.config.oauth2;

import java.util.Map;

public class KakaoUserInfo implements OAuth2UserInfo {

	private final Map<String,Object> attributes;
	
	public KakaoUserInfo(Map<String,Object> attributes) {
		this.attributes = attributes;
	}
	
	@Override
	public String getProviderId() {
		// TODO Auto-generated method stub
		return String.valueOf(attributes.get("id"));
	}

	@Override
	public String getProvider() {
		// TODO Auto-generated method stub
		return "Kakao";
	}

	@Override
	public String getEmail() {
		return (String)(((Map<String,Object>)attributes.get("kakao_account")).get("email"));
	}

	@Override
	public String getName() {
		return (String)attributes.get("email");
	}

}
