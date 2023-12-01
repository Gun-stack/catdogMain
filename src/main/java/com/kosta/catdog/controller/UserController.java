package com.kosta.catdog.controller;

import com.kosta.catdog.entity.User;
import com.kosta.catdog.repository.UserDslRepository;
import com.kosta.catdog.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class UserController {



	private final UserDslRepository userDslRepository;

	private final BCryptPasswordEncoder bCryptPasswordEncoder;

	private final UserRepository userRepository;


	// 회원가입
	@PostMapping("/userjoin")
	public String join(@RequestBody User user) {
		System.out.println("User Join!!");
		System.out.println(user.toString());
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		userRepository.save(user);
		return "joinsuccess";
	}

	// 아이디 중복 체크
	@GetMapping("/checkuserid")
	public String checkuserid(@RequestParam String id) {
		System.out.println("checkId : " + id);
		User user =  userDslRepository.findById(id);
		if(user == null){
			return "success";
		} else{
			return "fail";
		}

	}// 닉네임 중복 체크
	@GetMapping("/checkusernickname")
	public String checkusernickname(@RequestParam String nickname) {
		System.out.println("nickName : "+nickname);
		User user =  userDslRepository.findByNickname(nickname);
		if(user == null){
			return "success";
		} else {
			return "fail";
		}
	}
	// 계정 찾기
	public String findid(String tel, String password) {
		System.out.println("findId!!");
		return "";
	}

	// 비밀번호 찾기
	public String findpassword(String id, String password) {
		System.out.println("findPassword!!");
		return "";
	}
	// 회원정보 수정
	public User checkuser(String id, String password) {
		System.out.println("checkUser !!");
		return null;
	}
	// 닉네임 변경
	public void modinickname(String nickname) {
		System.out.println("modiNickname !!");
	}
	// 전화번호 변경
	public void moditel(String tel) {
		System.out.println("modiTel !!");
	}
	// 비밀번호 변경
	public void modipassword(String password) {
		System.out.println("modiPassword !!");
	}
	// 회원 탈퇴
	public void exit(String id, String password) {
		System.out.println("EXIT !!");
	}
}
