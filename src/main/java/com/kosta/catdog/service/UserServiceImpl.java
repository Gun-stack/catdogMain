package com.kosta.catdog.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kosta.catdog.entity.User;
import com.kosta.catdog.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public void join(User user) throws Exception {
		userRepository.save(user);
	}

	@Override
	public Boolean login(String id, String password) throws Exception {
		return true;
	}

	@Override
	public void updateUserInfo(User user) throws Exception {
		// TODO Auto-generated method stub

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
		return null;
	}

	@Override
	public User findId() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User findPassword() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void withdrawalUser(User user) throws Exception {
		// TODO Auto-generated method stub

	}
}