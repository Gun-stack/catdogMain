package com.kosta.catdog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kosta.catdog.dto.RegisterRequest;
import com.kosta.catdog.service.AuthService;

import lombok.AllArgsConstructor;

@RestController
// @RequestMapping("/api/auth")
@AllArgsConstructor
public class AuthController {
	@Autowired
	private AuthService authService;
	 
    @PostMapping("/newbie")
    public ResponseEntity<String> signup(@RequestBody RegisterRequest registerRequest) {
        authService.signup(registerRequest);
        return new ResponseEntity<>("회원가입 완료", HttpStatus.OK);
    }
 
    @GetMapping("accountVerification/{token}")
    public ResponseEntity<String> verifyAccount(@PathVariable String token) {
        authService.verifyAccount(token);
        return new ResponseEntity<>("이메일 인증이 완료 되었습니다.", HttpStatus.OK);
    }
}
