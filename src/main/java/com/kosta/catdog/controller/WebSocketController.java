//package com.kosta.catdog.controller;
//
//import org.springframework.messaging.handler.annotation.MessageMapping;
//import org.springframework.messaging.handler.annotation.SendTo;
//import org.springframework.stereotype.Controller;
//
//@Controller
//public class WebSocketController {
//
//    @MessageMapping("/sendMessage")
//    @SendTo("/topic/receivedMessage")
//    public String sendMessage(String message) {
//        // 메시지 처리 로직
//        return "Spring Boot received your message: " + message;
//    }
//}
