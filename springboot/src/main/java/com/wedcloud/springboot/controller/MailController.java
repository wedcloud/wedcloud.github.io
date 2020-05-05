package com.wedcloud.springboot.controller;

import com.wedcloud.springboot.util.ResponseBean;
import io.swagger.annotations.ApiOperation;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.InputStreamSource;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.InputStream;
import java.time.LocalDate;
import java.util.Date;

/**
 * @Author 许海斌
 * @create 2020/5/2 0002 12:33
 */
@RestController
@RequestMapping("/v1/mail")
public class MailController {

    @Resource private JavaMailSenderImpl mailSender;

    @ApiOperation("简单邮件发送")
    @GetMapping()
    public ResponseEntity<ResponseBean> sendMail(String text){
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setSubject("主题");
        simpleMailMessage.setSentDate(new Date());
        simpleMailMessage.setText(text);
        simpleMailMessage.setFrom("1007518009@qq.com");
        simpleMailMessage.setTo("m18100792153_1@163.com","xuhb@hitrobotgroup.com");
        mailSender.send(simpleMailMessage);
        return ResponseEntity.ok(ResponseBean.ok("邮件发送成功"));
    }

    /**
     * 遗留问题 -- 文件名乱码
     * @param multipartFiles
     * @return
     * @throws Exception
     */
    @ApiOperation("复杂邮件发送")
    @PostMapping()
    public ResponseEntity<ResponseBean> sendMimeMail(@RequestParam("multipartFiles") MultipartFile[] multipartFiles) throws Exception {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage,true);
        helper.setSubject("主题");
        helper.setText("<p style='color:red'>邮件正文</p>",true);
        for (MultipartFile file: multipartFiles) {
            helper.addAttachment(file.getOriginalFilename(),file);
        }
        helper.setFrom("1007518009@qq.com");
        helper.setTo(new String[]{"m18100792153_1@163.com","xuhb@hitrobotgroup.com"});
        mailSender.send(mimeMessage);
        return ResponseEntity.ok(ResponseBean.ok("邮件发送成功"));
    }
}
