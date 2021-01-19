package cn.maxcj.lawpdf.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Data
@Component
public class EmailProperties {

    @Value("${spring.mail.username}")
    String username;
    @Value("${spring.mail.password}")
    String password;
    @Value("${spring.mail.port}")
    Integer smtpPort;
    @Value("${spring.mail.host}")
    String smtpHost;

}
