package com.kosta.catdog.service;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kosta.catdog.dto.NotificationEmail;
import com.kosta.catdog.dto.RegisterRequest;
import com.kosta.catdog.entity.User;
import com.kosta.catdog.entity.VerificationToken;
import com.kosta.catdog.mail.CustomException;
import com.kosta.catdog.mail.MailContentBuilder;
import com.kosta.catdog.repository.UserRepository;
import com.kosta.catdog.repository.verificationTokenRepository;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@AllArgsConstructor
public class AuthService {
	@Autowired
	private UserRepository userRepository;
	@Autowired
    private verificationTokenRepository verificationTokenRepository;
	@Autowired
    private MailContentBuilder mailContentBuilder;
	@Autowired
    private MailService mailService;
	@Autowired
	BCryptPasswordEncoder passwordEncoder;
 
    @Transactional
    public void signup(RegisterRequest registerRequest) {
        User user = new User();
        user.setEmail(registerRequest.getEmail());
        user.setPassword(encodePassword(registerRequest.getPassword()));
        // user.setState("false");
 
        userRepository.save(user);
 
        String token = generateVerificationToken(user);
        String link = Constants.ACTIVATION_EMAIL + "/" + token;
        String message = mailContentBuilder.build(link);
 
        NotificationEmail notificationEmail = new NotificationEmail(user.getEmail(), "안녕하세요. 댕냥꽁냥입니다.", "인증 코드를 확인해주세요. " + message);
        mailService.sendMail(notificationEmail);
    }
 
    private String generateVerificationToken(User user) {
        String token = UUID.randomUUID().toString();
        VerificationToken verificationToken = new VerificationToken();
        verificationToken.setToken(token);
        verificationToken.setUser(user);
        verificationTokenRepository.save(verificationToken);
        return token;
    }
 
    private String encodePassword(String password) {
        return passwordEncoder.encode(password);
    }
 
    public void verifyAccount(String token) {
        Optional<VerificationToken> verificationTokenOptional = verificationTokenRepository.findByToken(token);
        verificationTokenOptional.orElseThrow(() -> new CustomException("잘못된 토큰입니다."));
        fetchUserAndEnable(verificationTokenOptional.get());
    }
 
    @Transactional
    public void fetchUserAndEnable(VerificationToken verificationToken) {
        String email = verificationToken.getUser().getEmail();
        User user = userRepository.findByEmail(email).orElseThrow(() -> new CustomException("유저를 찾을 수 없습니다. " + email));
        user.setState("true");
        userRepository.save(user);
    }
}
