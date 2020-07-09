package com.liao.controller;

import com.liao.service.impl.MailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * TODO: 邮件拦截层
 * @author LiAo
 * @date 2020/5/21 9:12
 */
@RequestMapping("/mail")
@Controller
public class EmailController {

    private final MailServiceImpl mailService;

    @Autowired
    public EmailController(MailServiceImpl mailService) {
        this.mailService = mailService;
    }

    private String email = "liao991224@gmail.com";

    /**
     * 文本邮件
     * @return
     */
    @GetMapping("/simple")
    @ResponseBody
    private Map<String ,Object> sendSimpleMail(){
        Map<String ,Object> map = new HashMap<>();
        try {
            mailService.simpleMail(email);
        } catch (Exception e) {
            e.printStackTrace();
            map.put("res","error");
        }

        return map;
    }

    /**
     * html邮件
     * @return
     */
    @GetMapping("/htmlMail")
    @ResponseBody
    private Map<String ,Object> htmlMail(){
        Map<String ,Object> map = new HashMap<>();
        try {
            mailService.htmlMail(email);
        } catch (Exception e) {
            e.printStackTrace();
            map.put("res","error");
        }

        return map;
    }

    /**
     * 附件邮件
     * @return
     */
    @GetMapping("/attachmentsMail")
    @ResponseBody
    private Map<String ,Object> attachmentsMail(){
        Map<String ,Object> map = new HashMap<>();
        try {
            mailService.attachmentMail(email);
        } catch (Exception e) {
            e.printStackTrace();
            map.put("res","error");
        }

        return map;
    }

    /**
     * 图片邮件
     * @return
     */
    @GetMapping("/imgMail")
    @ResponseBody
    private Map<String ,Object> imgMail(){
        Map<String ,Object> map = new HashMap<>();
        try {
            mailService.imgMail(email);
        } catch (Exception e) {
            e.printStackTrace();
            map.put("res","error");
        }

        return map;
    }

    /**
     * 模板邮件
     * @return
     */
    @GetMapping("/templateMail")
    @ResponseBody
    private Map<String ,Object> templateMail(){
        Map<String ,Object> map = new HashMap<>();
        try {
            mailService.TemplateMail(email);
        } catch (Exception e) {
            e.printStackTrace();
            map.put("res","error");
        }
        return map;
    }


}
