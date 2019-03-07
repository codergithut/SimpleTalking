package com;

import org.junit.Test;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 * @ProjectName: com
 * @Description: 一句话描述该类的功能
 * @Author: tianjian
 * @CreateDate: 2019/3/7
 * @UpdateUser: tianjian
 * @UpdateDate: 2019/3/7
 * @UpdateRemark: 跟新说明
 * @Version: [v1.0]
 */
public class EmailTest {


    /**
     *  mail:
     *     host: smtp.outlook.com
     *     username: prcetl@pernod-ricard.com
     *     password: Pernod2016
     *     port: 587
     *     protocol: smtp
     *     test-connection: false
     *     default-encoding: UTF-8
     *     properties:
     *       mail.smtp.socketFactory.fallback : true
     *       mail.smtp.starttls.enable: true
     *     subject:
     *       untreatedOrder: 【System Alert】 请及时处理订单
     */

    @Test
    public void emailTest() throws MessagingException {
        JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
        javaMailSender.setHost("smtp.163.com");
        javaMailSender.setUsername("15151837009@163.com");
        javaMailSender.setPassword("l1016482011");
        javaMailSender.setPort(587);
        javaMailSender.setProtocol("smtp");
        javaMailSender.setDefaultEncoding("UTF-8");
        Properties properties = new Properties();
        properties.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        properties.setProperty("mail.smtp.socketFactory.fallback", "true");
        properties.setProperty("mail.smtp.starttls.enable", "true");
        properties.setProperty("mail.smtp.auth", "true");
        properties.setProperty("mail.starttls.required", "true");
        properties.setProperty("mail.smtp.starttls.enable", "true");
       // properties.setProperty("mail.smtp.socketFactory.class", "SSL_FACTORY");
        javaMailSender.setJavaMailProperties(properties);


        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
        messageHelper.setFrom("15151837009@163.com");
        messageHelper.setTo("1468195034@qq.com");
        messageHelper.setSubject("测试哈哈哈");
        messageHelper.setText("你好我是个测试邮件", true);

        javaMailSender.send(mimeMessage);

    }
}
