package com.kosta.catdog.mail;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class MailContentBuilder {
		 
    public String build(String message) {
    	return "<a href=\"" + message + "\" target=\"_blank\">인증 링크</a>";
    }
}
