package com.kosta.catdog.controller;

import com.kosta.catdog.config.auth.PrincipalDetails;
import com.kosta.catdog.config.jwt.JwtProperties;
import com.kosta.catdog.dto.LoginRequestDto;
import com.kosta.catdog.entity.User;
import com.kosta.catdog.repository.UserDslRepository;
import com.kosta.catdog.repository.UserRepository;
import com.kosta.catdog.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class UserController {


    private final UserDslRepository userDslRepository;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    private final UserRepository userRepository;

    private final UserService userService;


    // 유저 정보 조회
    @GetMapping("/user")
    public ResponseEntity<User> user(Authentication authentication) {
        System.out.println("User Controller ===========");
        PrincipalDetails principalDetails = (PrincipalDetails) authentication.getPrincipal();
        System.out.println("User Id  : " + principalDetails.getUser().getId());
        System.out.println("User Nickname : " + principalDetails.getUser().getNickname());
        System.out.println("User Role : " + principalDetails.getUser().getRoles());
        return new ResponseEntity<User>(principalDetails.getUser(), HttpStatus.OK);
    }


    // 회원가입
    @PostMapping("/userjoin")
    public String join(@RequestBody User user) {
        try {
            user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
            userService.join(user);
            return "joinsuccess";
        } catch (Exception e) {
            e.printStackTrace();
            return "fail";
        }
    }

    // 아이디 중복 체크
    @GetMapping("/checkuserid")
    public ResponseEntity<String> checkuserid(@RequestParam String id) {
        System.out.println("Id : " + id);
        try {
            String res = userService.isUserIdDuplicate(id);
            return new ResponseEntity<String>(res, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
        }
    }

    // 닉네임 중복 체크
    @GetMapping("/checkusernickname")
    public ResponseEntity<String> checkusernickname(@RequestParam String nickname) {
        try {
            String res = userService.isNicknameDuplicate(nickname);
            return new ResponseEntity<String>(res, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<String>( HttpStatus.BAD_REQUEST);
        }
    }

    // 계정 찾기
    public String findid(User user) {
        System.out.println("findId!!");
        System.out.println(user.getTel());
        System.out.println(user.getPassword());
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
