package cn.maxcj.lawpdf.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Component;

import java.util.Properties;

@Component
public class EmailConfig {

    @Autowired
    private EmailProperties emailProperties;

    @Bean
    public JavaMailSender javaMailSender() {
        JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
        javaMailSender.setUsername(emailProperties.getUsername());
        javaMailSender.setPassword(emailProperties.getPassword());
        javaMailSender.setHost(emailProperties.getSmtpHost());
        javaMailSender.setPort(emailProperties.getSmtpPort());
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.host", emailProperties.getSmtpHost());
        props.put("mail.store.protocol", "smtp");
        props.put( "mail.smtp.port", emailProperties.getSmtpPort() );
        //开启SSL
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.socketFactory.port",emailProperties.getSmtpPort());
        props.put("mail.smtp.socketFactory.fallback","false");
        javaMailSender.setJavaMailProperties(props);
        return javaMailSender;
    }
}