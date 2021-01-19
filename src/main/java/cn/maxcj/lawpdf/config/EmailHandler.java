package cn.maxcj.lawpdf.config;


import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Component;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.util.Arrays;

@Slf4j
@Component
public class EmailHandler {

    @Autowired
    private EmailProperties emailProperties;

    @Autowired
    private JavaMailSender javaMailSender;

    public void sendEmail(String subject, String content, String filePath, Boolean isHtmlEmail, String[] ReceiveEmails, String[] ccEmail) {
        try {
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true, "utf-8");
            messageHelper.setFrom(emailProperties.getUsername());
            messageHelper.setTo(ReceiveEmails);
            messageHelper.setCc(ccEmail);
            messageHelper.setSubject(subject);
            messageHelper.setText(content, isHtmlEmail);

            if (StringUtils.isNotBlank(filePath)) {
                File file = new File(filePath);
                messageHelper.addAttachment(file.getName(), file);
            }
            javaMailSender.send(mimeMessage);
            log.info("email success fromUser:{} toUser:{} subject:{} content:{} filePath:{}",
                    emailProperties.getUsername(), Arrays.toString(ReceiveEmails), subject, content, filePath);
        } catch (MessagingException e) {
            log.error("email exception fromUser:{} toUser:{} subject:{} content:{} filePath:{}",
                    emailProperties.getUsername(), Arrays.toString(ReceiveEmails), subject, content, filePath);
        }
    }


    public void sendEmailAsText() {
        MimeMessagePreparator mimeMessagePreparator = mimeMessage -> {
            mimeMessage.setFrom(emailProperties.getUsername());
            mimeMessage.setRecipients(Message.RecipientType.TO, "903283542@11.com");
            mimeMessage.setText("test email");
        };
        try {
            this.javaMailSender.send(mimeMessagePreparator);
            log.info("邮箱已返送至[{}]邮箱！", "903283542");
        } catch (MailException e) {
            log.error("邮箱异常：{}", e);
        }
    }


}
