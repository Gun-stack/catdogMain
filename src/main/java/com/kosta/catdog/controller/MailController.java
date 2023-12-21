package com.kosta.catdog.controller;

import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kosta.catdog.dto.NotificationEmail;
import com.kosta.catdog.entity.User;
import com.kosta.catdog.mail.MailContentBuilder;
import com.kosta.catdog.repository.UserDslRepository;
import com.kosta.catdog.repository.UserRepository;
import com.kosta.catdog.repository.verificationTokenRepository;
import com.kosta.catdog.service.AuthService;
import com.kosta.catdog.service.MailService;
import com.kosta.catdog.service.UserService;

@RestController
public class MailController {
	@Autowired
	MailService mailService;
	@Autowired
	UserRepository userRepository;
	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;
	@Autowired
	UserService userService;
	@Autowired
	AuthService authService;
	@Autowired
	verificationTokenRepository verificationTokenRepository;
	@Autowired
	MailContentBuilder mailContentBuilder;
	@Autowired
	UserDslRepository userDslRepository;
	
		// 임시 비밀번호 전송
		@PostMapping("/temppasswordemail")
		public void sendTempPasswordEmail(@RequestParam String id, @RequestParam String email) {
			try {
				User user = userDslRepository.findById_AndEmail(id, email);
				String userEmail = user.getEmail();
				
				Random random = new Random();
				Integer tempPassword = random.nextInt(1000000);
								
				user.setPassword(bCryptPasswordEncoder.encode(tempPassword.toString()));
				userRepository.save(user);				
				
				NotificationEmail notiEmail = new NotificationEmail(userEmail, "안녕하세요. 댕냥꽁냥입니다.", "임시 비밀번호를 확인해주세요. " + tempPassword);
				System.out.println(notiEmail);
				mailService.sendMail(notiEmail);	
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
	
	// 계정 찾기 아이디 전송
	@GetMapping("/findid")
    public void findid(@RequestParam String email) {
    	try {
    		String id = userService.findId(email);
    		System.out.println(id);
    		if(id != null) {
    			NotificationEmail notiEmail = new NotificationEmail(email, "안녕하세요. 댕냥꽁냥입니다.", "아이디를 확인해주세요. " + id);
    			System.out.println(notiEmail);
    			mailService.sendMail(notiEmail);    			
    		} else {
    			System.out.println("해당 ID가 DB에 없습니다.");
    		}
    	} catch(Exception e) {
    		e.printStackTrace();
    	}
    }	
		
		// 회원가입 이메일 중복 검사 및 인증 번호 전송
		@GetMapping("/verify")
		public Integer sendVerificationCode(@RequestParam String email) {
			Random random = new Random();
			Integer verificationCode = random.nextInt(1000000);
			Optional<User> optionalUser = userRepository.findByEmail(email);
			try {
				if(optionalUser.isPresent()) {
					System.out.println("중복되는 ID가 있습니다.");
				} else {
					NotificationEmail notiEmail = new NotificationEmail(email, "안녕하세요. 댕냥꽁냥입니다.", "인증 코드를 확인해주세요. " + verificationCode);
					System.out.println(notiEmail);
					mailService.sendMail(notiEmail);
				}
			} catch(Exception e) {
				e.printStackTrace();
			}
			return verificationCode;
		}
}
