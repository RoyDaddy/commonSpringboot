package com.neok.commonSpringboot.util;

import java.util.Properties;

import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

//@Component("mailSender")
public class MailSender {
//	@Autowired
    public JavaMailSender emailSender;
//	@Autowired
    public static MailProperties mailProp;
	
	private static final String MAIL_HOST = mailProp.getMailHost();
    private static final Integer MAIL_PORT = mailProp.getMailPort();
    private static final String MAIL_USERNAME = mailProp.getMailUserName();
    private static final String MAIL_PASSWORD = mailProp.getMailPassword();
    private static final String MAIL_TRANSPORT_PROTOCOL = mailProp.getMailTransportProtocol();
    private static final String MAIL_SMTP_AUTH = mailProp.getMailSmtpAuth();
    private static final String MAIL_SMTP_STARTTLS_ENABLE = mailProp.getMailSmtpStarttlsEnable();
    private static final String MAIL_DEBUG = mailProp.getMailDebug();
    
//    @Bean
    public JavaMailSender getJavaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost(MAIL_HOST);
        mailSender.setPort(MAIL_PORT);
        //추후 config context 와 properties 추가
        mailSender.setUsername(MAIL_USERNAME);
        mailSender.setPassword(MAIL_PASSWORD);

        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", MAIL_TRANSPORT_PROTOCOL);
        props.put("mail.smtp.auth", MAIL_SMTP_AUTH);
        props.put("mail.smtp.starttls.enable", MAIL_SMTP_STARTTLS_ENABLE);
        props.put("mail.debug", MAIL_DEBUG);

        return mailSender;
    }

    public void sendSimpleMessage(String to, String subject, String text) {
//        SimpleMailMessage message = new SimpleMailMessage();
        MimeMessage message = emailSender.createMimeMessage();
        try{
            MimeMessageHelper messageHelper = new MimeMessageHelper(message, true, "UTF-8");
            message.setSubject(subject,"UTF-8");
            message.setText(text,"UTF-8","html");
            messageHelper.setFrom(MAIL_USERNAME, "OnejoyLife team");
            messageHelper.setTo(new InternetAddress(to,"", "UTF-8"));
            emailSender.send(message);
        }catch (Exception e){

        }
    }
}