package com.kosta.catdog.controller;

import org.springframework.web.bind.annotation.RestController;

import com.kosta.catdog.entity.User;

@RestController
public class UserController {

	
	// 회원가입
	public void join(User user) {
		System.out.println("User Join!!");
	}
	// 아이디 중복 체크
	public User checkid(String id) {
		System.out.println("checkId!!");
		return null;
	}
	// 닉네임 중복 체크
	public User checknickname(String nickname) {
		System.out.println("nickName!!");
		return null;
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
