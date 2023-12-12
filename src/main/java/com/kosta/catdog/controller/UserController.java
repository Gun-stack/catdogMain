package com.kosta.catdog.controller;

import com.kosta.catdog.config.auth.PrincipalDetails;
import com.kosta.catdog.config.jwt.JwtProperties;
import com.kosta.catdog.dto.LoginRequestDto;
import com.kosta.catdog.entity.Designer;
import com.kosta.catdog.entity.User;
import com.kosta.catdog.repository.UserDslRepository;
import com.kosta.catdog.repository.UserRepository;
import com.kosta.catdog.service.DesignerService;
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
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserDslRepository userDslRepository;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    private final UserRepository userRepository;

    private final UserService userService;

    private final DesignerService designerService;


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
    @PostMapping("/modinickname")
    public ResponseEntity<String> modinickname(@RequestBody Map<String, Object> requestBody) {
        Integer num = (Integer)requestBody.get("num");
        String nickname = (String)requestBody.get("nickname");

        try{
            String res = userService.modifyNickname(num, nickname);
            return new ResponseEntity<String>(res, HttpStatus.OK);
        } catch(Exception e){
            e.printStackTrace();
            return new ResponseEntity<String>( HttpStatus.BAD_REQUEST);
        }
    }

    // 전화번호 변경
    @PostMapping("/moditel")
    public ResponseEntity<String> moditel(@RequestBody Map<String, Object> requestBody) {
        Integer num = (Integer)requestBody.get("num");
        String userTel = (String)requestBody.get("userTel");

        try{
            String res = userService.modifyTel(num, userTel);
            return new ResponseEntity<String>(res, HttpStatus.OK);
        } catch(Exception e){
            e.printStackTrace();
            return new ResponseEntity<String>( HttpStatus.BAD_REQUEST);
        }
    }

    // 비밀번호 변경
    @PostMapping("/modipassword")
    public ResponseEntity<String> modipassword(@RequestBody Map<String, Object> requestBody) {
        Integer num = (Integer)requestBody.get("num");
//        String password = (String)requestBody.get("password");
//        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        String password = bCryptPasswordEncoder.encode(requestBody.get("password").toString());


        System.out.println("Num : " + num);
        System.out.println("password : " + password);

        try{
            User user = userService.findByNum(num);
            user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
            System.out.println("UserNum : " + user.getNum());
            String res = userService.modifyPassword(user.getNum(), password);
            return new ResponseEntity<String>(res, HttpStatus.OK);
        } catch(Exception e){
            e.printStackTrace();
            return new ResponseEntity<String>( HttpStatus.BAD_REQUEST);
        }
    }

    // 회원 탈퇴
    public void exit(String id, String password) {
        System.out.println("EXIT !!");
    }

    @PostMapping("/desreg")
    public ResponseEntity<Boolean> desreg(@RequestPart(value="file", required = false) List<MultipartFile> file
            , @RequestParam("id") String id
            , @RequestParam("desNickname") String desNickname
            , @RequestParam("position") String position) {
        try{
            User user = userService.getUserInfoById(id);
            // id , position, desnickname
            Designer des = new Designer();
            if(user.getRoles().equals("ROLE_USER")){ // 일반 회원이 미용사로 신청할 경우
                user.setRoles("ROLE_DES"); // user 권한 변경
                userService.modifyRole(id);
                des.setId(user.getId());
                des.setDesNickname(desNickname);
                des.setPosition(position);
            }else { // ROLE_SHOP 권한을 가진 사람이 신청할경우
                des.setId(user.getId());
                des.setDesNickname(desNickname);
                des.setPosition(position);
            }
            designerService.desreg(des, file);

            return new ResponseEntity<Boolean>(true, HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<Boolean>(false,HttpStatus.BAD_REQUEST);
        }

    }

}
