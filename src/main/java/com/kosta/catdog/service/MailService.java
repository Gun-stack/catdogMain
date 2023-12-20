package com.kosta.catdog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.kosta.catdog.dto.NotificationEmail;
import com.kosta.catdog.mail.CustomException;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@AllArgsConstructor
public class MailService {
	@Autowired
	private JavaMailSender mailSender;
	 
    @Async
	// 이메일을 비동기적으로 보내기 위해 @Async 어노테이션 사용
    public void sendMail(NotificationEmail notificationEmail) {
        MimeMessagePreparator messagePreparator = mimeMessage -> {
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
            messageHelper.setFrom("admin@catdog.com");
            messageHelper.setTo(notificationEmail.getRecipient());
            messageHelper.setSubject(notificationEmail.getSubject());
            messageHelper.setText(notificationEmail.getBody(), true);
        };
 
        try {
        	// JavaMailSender를 사용하여 이메일 전송
            mailSender.send(messagePreparator);
            log.info("메일이 전송되었습니다.");
        } catch (MailException e) {
        	// 이메일 보내기 도중 에러가 발생하면 예외를 처리하고 커스텀 예외를 던짐
            log.error(String.valueOf(e));
            throw new CustomException("메일을 이곳으로 보내는 중 에러 발생:  " + notificationEmail.getRecipient());
        }
    }        
}
