package com.kosta.catdog.service;


import com.kosta.catdog.entity.User;

public interface UserService {
	// 회원가입
	void join(User user) throws Exception;
	// 로그인
	Boolean login(String id, String password) throws Exception;
	// 회원정보수정
	void updateUserInfo(User user) throws Exception;
	// 아이디 중복체크
	boolean isUserIdDuplicate(String id) throws Exception;
	// 닉네임 중복체크
	boolean isNicknameDuplicate(String nickname) throws Exception;
	// 아이디로 회원정보 가져오기
	User getUserInfoById(String id) throws Exception;
	// 아이디 찾기
	User findId() throws Exception;
	// 비밀번호 찾기
	User findPassword() throws Exception;
	// 회원탈퇴
	void withdrawalUser(User user) throws Exception;
}
