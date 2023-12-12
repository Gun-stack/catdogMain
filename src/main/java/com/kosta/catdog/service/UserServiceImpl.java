package com.kosta.catdog.service;


import com.kosta.catdog.entity.Designer;
import com.kosta.catdog.repository.DesignerRepository;
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
	@Autowired
	private DesignerRepository designerRepository;
	@Override
	public void join(User user) throws Exception {
		userRepository.save(user);
		System.out.println("Get Roles : " + user.getRoles());
		if(user.getRoles().equals("ROLE_DES")){
			System.out.println("IF ROLE_DES !!!");
			String id = user.getId();
			Designer des = new Designer();
			des.setId(id);
			System.out.println(des.getId());
			designerRepository.save(des);
		}


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
	public String modifyNickname(Integer num, String nickname) throws Exception {
		userDslRepository.modifyNickname(num, nickname);
		return "success";
	}

	@Override
	public String modifyTel(Integer num, String tel) throws Exception {
		userDslRepository.modifyTel(num, tel);
		return "success";
	}

	@Override
	public String modifyPassword(Integer num, String password) throws Exception {
		userDslRepository.modifyPassword(num, password);
		return "success";
	}

	@Override
	public void modifyRole(String id) throws Exception {
		userDslRepository.modifyRole(id);
	}

	@Override
	public User findByNum(Integer num) throws Exception {
		return userDslRepository.findByNum(num);
	}


}