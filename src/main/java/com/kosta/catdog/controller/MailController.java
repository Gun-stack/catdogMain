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
    public ResponseEntity<String> sendTempPasswordEmail(@RequestParam String id, @RequestParam String email) {
        try {
            User user = userDslRepository.findById_AndEmail(id, email);
            
            if (user == null) {
            	System.out.println("사용자를 찾을 수 없습니다.");
                return new ResponseEntity<>("사용자를 찾을 수 없습니다.", HttpStatus.NOT_FOUND);
            }

            String userEmail = user.getEmail();
            
            Random random = new Random();
            Integer tempPassword = random.nextInt(1000000);

            user.setPassword(bCryptPasswordEncoder.encode(tempPassword.toString()));
            userRepository.save(user);

            NotificationEmail notiEmail = new NotificationEmail(userEmail, "안녕하세요. 댕냥꽁냥입니다.", "임시 비밀번호를 확인해주세요. " + tempPassword);
            System.out.println(notiEmail);
            mailService.sendMail(notiEmail);

            System.out.println("이메일이 성공적으로 전송되었습니다.");
            return new ResponseEntity<>("이메일이 성공적으로 전송되었습니다.", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("서버 오류가 발생했습니다.");
            return new ResponseEntity<>("서버 오류가 발생했습니다.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
	
	// 계정 찾기 아이디 전송
	@GetMapping("/findid")
    public ResponseEntity<String> findId(@RequestParam String email) {
        try {
            String id = userService.findId(email);
            if (id != null) {
                NotificationEmail notiEmail = new NotificationEmail(email, "안녕하세요. 댕냥꽁냥입니다.", "아이디를 확인해주세요. " + id);
                System.out.println(notiEmail);
                mailService.sendMail(notiEmail);

                System.out.println("이메일이 성공적으로 전송되었습니다.");
                return new ResponseEntity<>("이메일이 성공적으로 전송되었습니다.", HttpStatus.OK);
            } else {
                System.out.println("해당 이메일이 계정에 없습니다.");
                return new ResponseEntity<>("해당 이메일이 계정에 없습니다.", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("서버 오류가 발생했습니다.");
            return new ResponseEntity<>("서버 오류가 발생했습니다.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
			
	// 회원가입 이메일 중복 검사 및 인증 번호 전송
	@GetMapping("/verify")
    public ResponseEntity<String> sendVerificationCode(@RequestParam String email) {
        try {
            Random random = new Random();
            Integer verificationCode = random.nextInt(1000000);

            Optional<User> optionalUser = userRepository.findByEmail(email);

            if (optionalUser.isPresent()) {
            	System.out.println("중복되는 이메일이 이미 등록되어 있습니다.");
                return new ResponseEntity<>("중복되는 이메일이 이미 등록되어 있습니다.", HttpStatus.CONFLICT);
            } else {
                NotificationEmail notiEmail = new NotificationEmail(email, "안녕하세요. 댕냥꽁냥입니다.", "인증 코드를 확인해주세요. " + verificationCode);
                System.out.println(notiEmail);
                mailService.sendMail(notiEmail);

                System.out.println("이메일이 성공적으로 전송되었습니다.");
                return new ResponseEntity<>("이메일이 성공적으로 전송되었습니다.", HttpStatus.OK);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("서버 오류가 발생했습니다.");
            return new ResponseEntity<>("서버 오류가 발생했습니다.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
				
}
