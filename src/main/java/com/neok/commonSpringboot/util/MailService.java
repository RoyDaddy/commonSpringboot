package com.neok.commonSpringboot.util;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailService {
	@Autowired
    private JavaMailSender mailSender;
	@Autowired
	MailProperties mailProp;

    public void mailSend(Map<String,Object> params) {
        try {
            MailHandler mailHandler = new MailHandler(mailSender);
            
            // 받는 사람
           mailHandler.setTo((String)params.get("toAddress"));
            // 보내는 사람
           mailHandler.setFrom(mailProp.getMailUserName());
            // 제목
           mailHandler.setSubject((String)params.get("title"));
            // HTML Layout
           String htmlContent = 
            	"<p>" + 
            		(String)params.get("content") +
				"</p>";
           mailHandler.setText(htmlContent, true);
            // 첨부 파일
//           mailHandler.setAttach("newTest.txt", "static/originTest.txt");
            // 이미지 삽입
//           mailHandler.setInline("sample-img", "static/sample1.jpg");

           mailHandler.send();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}