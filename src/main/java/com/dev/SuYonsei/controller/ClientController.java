package com.dev.SuYonsei.controller;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSendException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dev.SuYonsei.model.Client;
import com.dev.SuYonsei.service.ClientService;
import com.dev.SuYonsei.service.EmailService;

@Controller
public class ClientController {

	@Autowired
	ClientService clientService;
	
	@Autowired
	EmailService emailService;
	
	@PostMapping("/clientInsert")
	@ResponseBody
	public String clientInsert(
			Client client,
			String code
			) throws IllegalStateException, IOException {
		
		clientService.clientInsert(client);
		
		ExecutorService executorService = Executors.newSingleThreadExecutor();
		
        // 작업1 (스레드)
        executorService.submit(() -> {
        	String lang = "";
    		if(client.getCode().equals("jp")) {
    			lang = "일본 사이트에서 문의가 발생 하였습니다.";
    		}else {
    			lang = "영문 사이트에서 문의가 발생 하였습니다.";
    		}
        	
        	try {
        		emailService.sendEmail(new String[]{"admin@smilelasek.com"}, "[WEB발신]" + lang,
        				"안녕하세요 저의 이름은 " + client.getName() + "입니다.\r\n"
        				+ "저의 관심 분야는 " + client.getSubject() + "입니다.\r\n"
        				+ "=========================\r\n"
        				+ "=========================\r\n" 
        				+ "* 연락처 : " + client.getPhone() + "\r\n"
        				+ "* 이메일 : " + client.getEmail() + "\r\n"
        				+ "* 이름 : " + client.getName()
        				);
        	}catch(MailSendException e) {
        		System.out.println(e);
        	} catch (InterruptedException e) {
        		e.printStackTrace();
        	}
    		
    		
        });

        executorService.shutdown();
		
		
		
		String msg = "";
		StringBuilder sb = new StringBuilder();
		
		if(code.equals("jp")) {
			msg = "ご相談要請承りました。担当の者がご連絡させていただきますので少しお待ちください。 スパムメールボックスにメールが送信される場合がありますのでご確認お願いします。";
			sb.append("alert('"+msg+"');");
			sb.append("location.href='/jp/index'");
		}else if(code.equals("en")){
			msg = "Your request has been received. We will be in touch shortly. Please check the email as it may be sent to your spam folder.";
			sb.append("alert('"+msg+"');");
			sb.append("location.href='/en/index'");
		}
		
		sb.insert(0, "<script>");
		sb.append("</script>");
		
		return sb.toString();
	}
}
