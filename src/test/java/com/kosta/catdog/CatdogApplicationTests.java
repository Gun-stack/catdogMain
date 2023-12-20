package com.kosta.catdog;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.kosta.catdog.dto.NotificationEmail;
import com.kosta.catdog.dto.RegisterRequest;
import com.kosta.catdog.entity.DesGallery;
import com.kosta.catdog.entity.User;
import com.kosta.catdog.entity.UserGalleryComment;
import com.kosta.catdog.repository.DesGalleryRepository;
import com.kosta.catdog.repository.UserDslRepository;
import com.kosta.catdog.repository.UserGalleryCommentRepository;
import com.kosta.catdog.repository.UserRepository;
import com.kosta.catdog.service.AuthService;
import com.kosta.catdog.service.MailService;
import com.kosta.catdog.service.UserService;

@SpringBootTest
class CatdogApplicationTests {
	@Autowired
	UserDslRepository userDslRepository;
	@Autowired
	DesGalleryRepository desGalleryRepository;
	@Autowired
	UserGalleryCommentRepository userGalleryCommentRepository;
	@Autowired
	UserRepository userRepository;
	@Autowired
	MailService mailService;
	@Autowired
	UserService userService;
	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;
	@Autowired
	AuthService authService;
	
	// @Test
	void contextLoads() {
		
	}
	
	// @Test
	void avgStarCountByDesigner() {
		try {
			Double avgStarCount = userDslRepository.findAvgStarCountByDesigner(1);
			System.out.println(avgStarCount);
		} catch(Exception e) {
			e.printStackTrace();
		}		
	}
	
	// @Test
	void desGalleryListMainPage() {
		try {
			Sort sort = Sort.by("date").descending();
			PageRequest pageRequest = PageRequest.of(0, 9, sort);
			Slice<DesGallery> desGalleryListMain = desGalleryRepository.findAll(pageRequest);
			System.out.println(desGalleryListMain);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	// @Test
	void desGalleryListShopPage() {
		try {
			List<DesGallery> desGalleryListShop = userDslRepository.findDesGalleryListShopPage(1, 0, 9);
			System.out.println(desGalleryListShop);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	// @Test
	void desGalleryListDesignerPage() {
		try {
			List<DesGallery> desGalleryListDesigner = userDslRepository.findDesGalleryListDesignerPage(2, 0, 9);
			System.out.println(desGalleryListDesigner);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	// @Test
	void findComment() {
		try {
			List<UserGalleryComment> comment = userDslRepository.findComment(1);
			System.out.println(comment);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	// @Test
	void findByEmail() {
		try {
			Optional<User> email = userRepository.findByEmail("jeon7738@naver.com");
			System.out.println(email.get().getEmail());
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	// @Test
	void sendTempPasswordEmail() {
		try {
			Optional<User> email = userRepository.findByEmail("jeon7738@naver.com");
			String emailString = email.get().getEmail();
			
			NotificationEmail notiEmail = new NotificationEmail(emailString, "안녕하세요. 댕냥꽁냥입니다.", "임시 비밀번호를 확인해주세요.");
			
			mailService.sendMail(notiEmail);
			
			System.out.println(notiEmail);			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	// @Test
	void sendVerificationEmail() {
		try {
			NotificationEmail notiEmail = new NotificationEmail("jeon7738@naver.com", "안녕하세요. 댕냥꽁냥입니다.", "인증링크를 눌러주세요.");
			mailService.sendMail(notiEmail);
			System.out.println(notiEmail);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	// @Test
	void findId() {
		try {
			String id = userDslRepository.findId("jeon7738@naver.com");
			System.out.println(id);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	// @Test
	void VerificationToJoin() {
		try {
			RegisterRequest registerRequest = new RegisterRequest("user4", "jeon7738@naver.com", "0000");
			authService.signup(registerRequest);
			System.out.println(registerRequest);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
