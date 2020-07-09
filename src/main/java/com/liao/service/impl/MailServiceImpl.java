package com.liao.service.impl;

import com.liao.service.IMailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.internet.MimeMessage;
import java.io.File;

/**
 *
 * TODO: 邮件发送类
 * @author LiAo
 * @date 2020/5/21 9:11
 */
@Service
public class MailServiceImpl implements IMailService {


    @Value("${spring.mail.username}")
    private String from;

    private final JavaMailSender javaMailSender;

    @Autowired
    public MailServiceImpl(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    /**
     * 简单文本邮件
     *
     * @param toUser
     * @throws Exception
     */
    @Override
    public void simpleMail(String toUser) throws Exception {
        // 初始化简单邮件对象
        SimpleMailMessage message = new SimpleMailMessage();

        // 邮件发送者
        message.setFrom(from);

        // 邮件接收者
        message.setTo(toUser);

        // 邮件标题
        message.setSubject("简单邮件");

        // 邮件内容
        message.setText("简单内容");

        // 发送邮件
        javaMailSender.send(message);
    }

    /**
     * html文本邮件
     *
     * @param toUser
     * @throws Exception
     */
    @Override
    public void htmlMail(String toUser) throws Exception {
        // 初始化MimeMessage 邮件对象
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setFrom(from);
        helper.setTo(toUser);
        helper.setSubject("html格式邮件");

        // 内容为html格式
        String cotent = "<p style='color:yellow;'>这是一封html格式的文件</p><h1>这是一封html格式的文件</h1>";

        // 为true表示以html格式发送
        helper.setText(cotent, true);
        javaMailSender.send(message);
    }

    /**
     * 带附件邮件
     *
     * @param toUser
     * @throws Exception
     */
    @Override
    public void attachmentMail(String toUser) throws Exception {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message,true);

        helper.setFrom(from);
        helper.setTo(toUser);
        helper.setSubject("带附件的邮件");

        // 加载绝对路径资源

        FileSystemResource fileSystemResource = new FileSystemResource(new File("D:\\Desktop\\20190512.docx"));
        helper.setText("这是一封带附件的邮件");

        // 添加邮件资源
        helper.addAttachment("",fileSystemResource);
        javaMailSender.send(message);
    }

    /**
     * 带图邮件
     *
     * @param toUser
     * @throws Exception
     */
    @Override
    public void imgMail(String toUser) throws Exception {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message,true);
         helper.setFrom(from);
         helper.setTo(toUser);
         helper.setSubject("带图邮件");

         // 设置资源的id
        String content = "<html><body>博客头像<img src='cid:img'/></body></html>";
        helper.setText(content,true);

        FileSystemResource fileSystemResource = new FileSystemResource(new File("E:\\图片\\IDEA\\12.jpg"));

        // 和上边cid对应
        helper.addInline("img",fileSystemResource);
        javaMailSender.send(message);
    }

    /**
     * 模板邮件
     *
     * @param toUser
     * @throws Exception
     */
    @Override
    public void TemplateMail(String toUser) throws Exception {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message,true);

        helper.setFrom(from);
        helper.setTo(toUser);
        helper.setSubject("测试模板邮件");

        Context context = new Context();
        TemplateEngine templateEngine = new TemplateEngine();
        // 给模板传入参数 username要与模板中的变量一致 promise为测试数据
        context.setVariable("username","LiAo");
        //thymeleaf模板默认会从src/main/resources/tempaltes目录下寻找文件，填入我们定义的模板名，不需要写后缀。
        String template = templateEngine.process("MailTemplate",context);

        helper.setText(template,true);

        javaMailSender.send(message);
    }
}
