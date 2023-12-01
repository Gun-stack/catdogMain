package com.kosta.catdog.config.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.kosta.catdog.entity.User;
import com.kosta.catdog.repository.UserDslRepository;

//security 설정에서 loginProcessingUrl("loginProc");
//loginProc 요청이 오면 자동으로 UserDetailsService 타입으로 IoC 되어있는 loadUserByusername 실행 
//(AuthenticationManager를 거쳐 AuthenticationProvider에 의해 호출됨_

@Service
public class PrincipalDetailsService implements UserDetailsService {

	@Autowired
	private UserDslRepository userDslRepository;
	// Security Session (내부 Authentication(내부 UserDetails))
	@Override
	public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
		User userEntity = userDslRepository.findByName(name);
		if (userEntity!=null) {
			return new PrincipalDetails(userEntity);
		}
		return null;
	}

}
