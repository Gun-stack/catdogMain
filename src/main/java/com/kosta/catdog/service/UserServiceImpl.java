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
		userRepository.save(user);
	}

	@Override
	public Boolean login(String id, String password) throws Exception {
		User user = userDslRepository.findById_AndPassword(id,password);
		return user==null? false:true;
	}

	@Override
	public boolean isUserIdDuplicate(String id) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isNicknameDuplicate(String nickname) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public User getUserInfoById(String id) throws Exception {
		return userDslRepository.findById(id);
	}

	@Override
	public User findId() throws Exception {
		// tel, password
		return null;
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
	public void updateNickname(Integer num, String nickname) throws Exception {
		userDslRepository.updateNickname(num, nickname);
	}

	@Override
	public void updateTel(Integer num, String tel) throws Exception {
		userDslRepository.updateTel(num, tel);
	}

	@Override
	public void updatePassword(Integer num, String password) throws Exception {
		userDslRepository.updatePassword(num, password);
	}
	
	
}