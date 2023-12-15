package com.kosta.catdog.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kosta.catdog.entity.Designer;
import com.kosta.catdog.entity.Shop;
import com.kosta.catdog.repository.DesignerRepository;
import com.kosta.catdog.repository.ShopRepository;
import com.kosta.catdog.repository.UserDslRepository;
import com.kosta.catdog.service.DesignerService;

@RestController
public class DesController {

    @Autowired
    private DesignerRepository designerRepository;

    @Autowired
    private UserDslRepository userDslRepository;

    @Autowired
    private ShopRepository shopRepository;


    @Autowired
    private DesignerService designerService;

    // 디자이너 정보 조회 고유번호 로 찾기
    @GetMapping("/desinfobynum")
    public ResponseEntity<Designer> DesInfo(@RequestParam Integer desNum) {
        System.out.println(desNum);
        try {
            Designer des = designerRepository.findById(desNum).get();
            return new ResponseEntity<Designer>(des, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<Designer>(HttpStatus.BAD_REQUEST);
        }

    }

    //디자이너 아이디로 정보찾기
    @GetMapping("/desinfobyid")
    public ResponseEntity<Object> DesInfoById(@RequestParam String desId) {
        System.out.println(desId);
        try {
            Map<String, Object> response = new HashMap<>();
            Designer des = userDslRepository.FindDesignerById(desId);
            Shop shop = userDslRepository.FindShopBySid(des.getSId());


            response.put("shop", shop);

            return new ResponseEntity<Object>(response, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("/shopdesinfobynum")
    public ResponseEntity<Object> ShopDesInfoByNum(@RequestParam Integer desNum) {
        try {
            Designer des = designerRepository.findById(desNum).get();
            Shop shop = userDslRepository.FindShopBySid(des.getSId());

            Map<String, Object> response = new HashMap<>();
            response.put("des", des);
            response.put("shop", shop);

            return new ResponseEntity<Object>(response, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
        }
    }


    //디자이너 프로필이미지 보기
    @GetMapping("/desimg/{num}")
    public void ImageView(@PathVariable Integer num, HttpServletResponse response) {
        try {
            designerService.fileView(num, response.getOutputStream());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 디자이너 sID로 찾기
    @GetMapping("/deslist")
    public ResponseEntity<List<Designer>> desListBySId(@RequestParam("sId") String sId) {
        try {
            List<Designer> desList = userDslRepository.findDesListBySId(sId);

            return new ResponseEntity<List<Designer>>(desList, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<List<Designer>>(HttpStatus.BAD_REQUEST);

        }
    }

    // 디자이너 등록
    public void regdes(Designer des) {
        System.out.println("redDes !!");
    }


    // 프로필 문구 수정
    public void modipro(String info) {
        System.out.println("modiProfile !!");
    }

}
