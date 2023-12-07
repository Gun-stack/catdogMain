package com.kosta.catdog.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kosta.catdog.entity.User;
import com.kosta.catdog.repository.UserDslRepository;
import com.kosta.catdog.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDslRepository userDslRepository;
	@Autowired
	private UserRepository userRepository;

	@Override
	public void join(User user) throws Exception {
		user.setRoles("ROLE_USER");
		userRepository.save(user);
	}

	@Override

	public Boolean login(String id, String password) throws Exception {

		User user = userDslRepository.findById_AndPassword(id,password);
		return user==null? false:true;
	}

	@Override
	public String isUserIdDuplicate(String id) throws Exception {
		User user = userDslRepository.findById(id);
		if(user != null ) {
			return "fail";
		}
		else return "success";
	}

	@Override
	public String isNicknameDuplicate(String nickname) throws Exception {
		User user = userDslRepository.findByNickname(nickname);
		if(user != null ) {
			return "fail";
		}
		else return "success";
	}

	@Override
	public User getUserInfoById(String id) throws Exception {
		return userDslRepository.findById(id);
	}

	@Override
	public String findId(String email) throws Exception {
		return userDslRepository.findIdByEmail(email);
	}

	@Override
	public User findPassword() throws Exception {
		// email
		return null;
	}

	@Override
	public void withdrawalUser(User user) throws Exception {
		// 작성 보류
	}

	@Override
	public void modifyNickname(Integer num, String nickname) throws Exception {
		userDslRepository.modifyNickname(num, nickname);
	}

	@Override
	public void modifyTel(Integer num, String tel) throws Exception {
		userDslRepository.modifyTel(num, tel);
	}

	@Override
	public void modifyPassword(Integer num, String password) throws Exception {
		userDslRepository.modifyPassword(num, password);
	}
	
	
}